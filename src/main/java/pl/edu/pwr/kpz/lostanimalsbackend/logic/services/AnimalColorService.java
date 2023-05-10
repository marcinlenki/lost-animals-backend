package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalColorRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalColor;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimalColorService {

    private final AnimalColorRepository animalColorRepository;

    public List<AnimalColor> getAnimalColorList(){
        return this.animalColorRepository.findAll();
    }

    public AnimalColor getAnimalColorById(Integer id){
        return this.animalColorRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "animal color with id: " + id + " dose not exists"
                ));
    }

    public void addAnimalColor(AnimalColor animalColor){
        this.animalColorRepository.save(animalColor);
    }

    public void deleteAnimalColorById(Integer id){
        boolean exists = animalColorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("animal color with id:" + id + " dose not exists");
        }
        this.animalColorRepository.deleteById(id);
    }

    public void updateAnimalColor(Integer id, AnimalColor animalColor){
        boolean exists = animalColorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("animal color with id:" + id + " dose not exists");
        }
        this.animalColorRepository.save(animalColor);
    }
}
