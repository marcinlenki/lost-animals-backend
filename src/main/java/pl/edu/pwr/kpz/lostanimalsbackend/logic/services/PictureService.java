package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pwr.kpz.lostanimalsbackend.config.FileStorageConfig;
import pl.edu.pwr.kpz.lostanimalsbackend.exceptions.FileUploadException;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.PictureRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;
import pl.edu.pwr.kpz.lostanimalsbackend.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Transactional
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;

    private final AmazonS3 amazonS3;
    
    private final FileStorageConfig fileStorageConfig;

    @Value("${s3.bucket.name}")
    private String s3BucketName;

    public AnimalPicture getPictureById(Integer id){
        return this.pictureRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("picture with id: " + id + " dose not exists"));
    }

    public AnimalPicture addPicture(AnimalPicture animalPicture) {
        return pictureRepository.save(animalPicture);
    }

    public void deletePictureById(Integer id) throws Exception {
        var picture = pictureRepository.findById(id).orElseThrow();
        URI pictureURI = new URI(picture.getUrl());
        String key = pictureURI.getPath().substring(1);

        pictureRepository.deleteById(id);

        var dor = new DeleteObjectRequest(s3BucketName, key);
        amazonS3.deleteObject(dor);
    }

    public AnimalPicture save(MultipartFile multipartFile, Integer animalId) {
        try {
            return saveAsync(multipartFile, animalId).get(10, TimeUnit.SECONDS);

        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public CompletableFuture<AnimalPicture> saveAsync(MultipartFile multipartFile, Integer animalId) throws FileUploadException {
        if (multipartFile == null)
            throw new FileUploadException("Multipart file is null!");

        File file = null;
        boolean uploadedToS3 = false;
        String generatedFilename = FileUtils.generateStorageFilename(multipartFile);

        try {
            Path resultFilepath = Path.of(fileStorageConfig.getImagesDirPath(), generatedFilename).toAbsolutePath();
            file = FileUtils.convertMultipartToFile(multipartFile, resultFilepath);

            PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, generatedFilename, file);
            amazonS3.putObject(putObjectRequest);
            uploadedToS3 = true;

            String objectUrl = amazonS3.getUrl(s3BucketName, generatedFilename).toString();

            AnimalPicture animalPicture =
                    AnimalPicture.builder()
                    .animal(Animal.builder().id(animalId).build())
                    .contentType(multipartFile.getContentType())
                    .url(objectUrl)
                    .build();

            var ret = addPicture(animalPicture);
            return CompletableFuture.completedFuture(ret);

        } catch (Exception e) {
            if (uploadedToS3) {
                var deleteObjectRequest = new DeleteObjectRequest(s3BucketName, generatedFilename);
                amazonS3.deleteObject(deleteObjectRequest);
            }

            throw new FileUploadException("Unable to save image " + multipartFile.getOriginalFilename(), e);

        } finally {
            if (file != null) {
                try {
                    Files.delete(file.toPath());

                } catch (IOException e) {
                    System.out.println("Unable to locally delete file " + file.getAbsolutePath());
                }
            }
        }
    }
}
