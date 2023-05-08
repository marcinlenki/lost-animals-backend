package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    public List<Animal> list() {
        return animalRepository.findAll();
    }
}