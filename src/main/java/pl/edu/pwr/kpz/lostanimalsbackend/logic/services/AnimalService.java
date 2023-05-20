package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimalService {

    private final PictureService pictureService;

    private final AnimalRepository animalRepository;

    public List<Animal> getAnimalList(){
        return this.animalRepository.findAll();
    }

    public Animal getAnimalById(Integer id){
        return this.animalRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "animal with id: " + id + " dose not exists"
                ));
    }

    public void addAnimal(Animal animal){
        this.animalRepository.save(animal);
    }

    public void deleteAnimalById(Integer id){
        boolean exists = animalRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("animal with id:" + id + " dose not exists");
        }
        this.animalRepository.deleteById(id);
    }

    public void updateAnimal(Integer id, Animal animal){
        boolean exists = animalRepository.existsById(id);

        if(!exists) {
            throw new IllegalStateException("animal with id:" + id + " dose not exists");
        }
        this.animalRepository.save(animal);
    }

    public List<AnimalPicture> getAnimalPictures(int animalId) {
        if (!animalRepository.existsById(animalId))
            throw new EntityNotFoundException();

        return pictureService.getPicturesByAnimalId(animalId);
    }


    public Object uploadAnimalPictures(MultipartFile[] files, int animalId) throws RuntimeException {
        if (!animalRepository.existsById(animalId))
            throw new EntityNotFoundException();

        else if (files == null || files.length == 0) {
            throw new IllegalArgumentException();
        }

        return files.length == 1 ? pictureService.save(files[0], animalId) : pictureService.saveMultiple(files, animalId);
    }


    public void deleteAnimalPicture(int animalId, int pictureId) {
        if (!animalRepository.existsById(animalId))
            throw new EntityNotFoundException();


        pictureService.deletePictureById(pictureId);
    }

    public void deleteAnimalPictures(int animalId, String ids) throws RuntimeException {
        if (!animalRepository.existsById(animalId))
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