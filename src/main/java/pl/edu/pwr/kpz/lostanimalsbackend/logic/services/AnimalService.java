package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.AnimalRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.AnimalResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.AnimalDTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;

import java.util.Arrays;
import java.util.List;

@Service
public class AnimalService extends MappedCrudService<Animal, AnimalRequestDTO, AnimalResponseDTO> {

    private final PictureService pictureService;

    public AnimalService(BaseRepository<Animal> repository, AnimalDTOMapper mapper, PictureService pictureService) {
        super(repository, LoggerFactory.getLogger(AnimalService.class), mapper);
        this.pictureService = pictureService;
    }

    protected Animal addSimple(Animal animal) {
        return repository.save(animal);
    }

    public List<AnimalPicture> getAnimalPictures(int animalId) {
        if (!repository.existsById(animalId))
            throw new EntityNotFoundException();

        return pictureService.getPicturesByAnimalId(animalId);
    }


    public Object uploadAnimalPictures(MultipartFile[] files, int animalId) throws RuntimeException {
        if (!repository.existsById(animalId))
            throw new EntityNotFoundException();

        else if (files == null || files.length == 0) {
            throw new IllegalArgumentException();
        }

        return files.length == 1 ? pictureService.save(files[0], animalId) : pictureService.saveMultiple(files, animalId);
    }


    public void deleteAnimalPicture(int animalId, int pictureId) {
        if (!repository.existsById(animalId))
            throw new EntityNotFoundException();


        pictureService.deletePictureById(pictureId);
    }

    public void deleteAnimalPictures(int animalId, String ids) throws RuntimeException {
        if (!repository.existsById(animalId))
            throw new EntityNotFoundException();

        // If one of the passed id is unparsable to int, NumberFormatException will be thrown
        // and the stream will be terminated.
        // Negative values are ignored.

        try {
            deleteAnimalPictures(Arrays
                    .stream(ids.split(","))
                    .map(Integer::parseInt)
                    .filter(id -> id > 0)
                    .toList());

        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid id value!");
        }
    }

    private void deleteAnimalPictures(List<Integer> ids) {
        pictureService.deletePicturesByIdInBatch(ids);
    }
}