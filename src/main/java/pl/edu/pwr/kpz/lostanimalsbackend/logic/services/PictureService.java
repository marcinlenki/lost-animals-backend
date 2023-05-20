package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pwr.kpz.lostanimalsbackend.config.FileStorageConfig;
import pl.edu.pwr.kpz.lostanimalsbackend.exceptions.FileUploadException;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.PictureRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.MultipleImageUploadDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;
import pl.edu.pwr.kpz.lostanimalsbackend.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Transactional
@RequiredArgsConstructor
public class PictureService {
    private static final Logger logger = LoggerFactory.getLogger(PictureService.class);

    private final PictureRepository pictureRepository;

    private final AmazonS3 amazonS3;
    
    private final FileStorageConfig fileStorageConfig;

    @Value("${s3.bucket.name}")
    private String s3BucketName;

    public List<AnimalPicture> getPicturesByAnimalId(int animalId) {
        return pictureRepository.findByAnimal_Id(animalId);
    }

    public AnimalPicture getPictureById(Integer id){
        return this.pictureRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("picture with id: " + id + " dose not exists"));
    }

    public AnimalPicture save(MultipartFile multipartFile, Integer animalId) throws RuntimeException {
        try {
            return saveAsync(multipartFile, animalId).get(10, TimeUnit.SECONDS);

        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public MultipleImageUploadDTO saveMultiple(MultipartFile[] multipartFiles, Integer animalId) {
        List<AnimalPicture> results = new LinkedList<>();
        List<CompletableFuture<AnimalPicture>> asyncResults = new LinkedList<>();
        final List<Exception> occurredExceptions = new LinkedList<>(); // report back to the client which images failed

        // start async processing
        for (var image : multipartFiles) {

            // if exceptions occurs during upload to S3, the returned object will be null.
            var asyncResult =
                    saveAsync(image, animalId)
                    .exceptionally(t -> {
                        logger.error(t.getMessage());
                        occurredExceptions.add(new Exception(t));
                        return null;
                    });

            asyncResults.add(asyncResult);
        }

        // get results and block if necessary
        for (var imageUpload : asyncResults) {
            try {
                var result = imageUpload.get(10, TimeUnit.SECONDS);

                if (result != null)
                    results.add(result);

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                occurredExceptions.add(e);
            }
        }

        return new MultipleImageUploadDTO(results, occurredExceptions);
    }

    @Async
    public CompletableFuture<AnimalPicture> saveAsync(MultipartFile multipartFile, Integer animalId) throws FileUploadException {
        if (multipartFile == null) {
            var e = new FileUploadException("Multipart file is null!");
            logger.error(e.getMessage(), e);
            throw e;
        }

        File file = null;

        String generatedFilename = FileUtils.generateStorageFilename(multipartFile);

        try {
            Path resultFilepath = Path.of(fileStorageConfig.getImagesDirPath(), generatedFilename).toAbsolutePath();
            file = FileUtils.convertMultipartToFile(multipartFile, resultFilepath);

            String objectUrl = amazonS3.getUrl(s3BucketName, generatedFilename).toString();

            AnimalPicture animalPicture =
                    AnimalPicture.builder()
                            .animal(Animal.builder().id(animalId).build())
                            .contentType(multipartFile.getContentType())
                            .url(objectUrl)
                            .build();


            PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, generatedFilename, file);

            // if upload to S3 fails, automatic rollback will be performed.
            amazonS3.putObject(putObjectRequest);

            var ret = addPicture(animalPicture);
            return CompletableFuture.completedFuture(ret);

        } catch (Exception e) {
            var ex = new FileUploadException("Unable to save image " + multipartFile.getOriginalFilename(), e);
            logger.error(ex.getMessage(), ex);
            throw ex;

        } finally {
            if (file != null) {
                try {
                    Files.delete(file.toPath());

                } catch (IOException e) {
                    logger.error("Unable to locally delete file " + file.getAbsolutePath() + ", the fill will remain on the local filesystem.");
                }
            }
        }
    }

    public AnimalPicture addPicture(AnimalPicture animalPicture) {
        return pictureRepository.save(animalPicture);
    }

    @Async
    public void deletePictureById(int id) throws RuntimeException {
        var picture = pictureRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        URI pictureURI = null;

        try {
            pictureURI = new URI(picture.getUrl());

        } catch (URISyntaxException ignored) {
            // ignore exception -> URI is fetched from database
            // and should always be correct as it is computed automatically
        }

        // extract the key (filename) from the url
        // should never throw NullPointerException
        String key = Objects.requireNonNull(pictureURI).getPath().substring(1);

        pictureRepository.deleteById(id);

        // if deletion from S3 fails, automatic rollback will be performed.
        var deleteObjectRequest = new DeleteObjectRequest(s3BucketName, key);
        amazonS3.deleteObject(deleteObjectRequest);
    }

    public void deletePicturesByIdInBatch(List<Integer> ids) throws RuntimeException {
        // not existing pictures are ignored
        ids.stream()
                .filter(pictureRepository::existsById)
                .forEach(this::deletePictureById);
    }
}
