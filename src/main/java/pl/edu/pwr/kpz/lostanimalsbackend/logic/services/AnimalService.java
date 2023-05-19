package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.AnimalDTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalDTOMapper animalDTOMapper;

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
        if(!exists){
            throw new IllegalStateException("animal with id:" + id + " dose not exists");
        }
        this.animalRepository.save(animal);
    }

}