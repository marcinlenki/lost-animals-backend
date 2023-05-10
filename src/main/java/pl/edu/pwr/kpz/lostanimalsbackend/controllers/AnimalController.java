package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.AnimalService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;

import java.util.List;

@RestController
@RequestMapping("animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public List<Animal> getAnimalList(){
        return this.animalService.getAnimalList();
    }

    @GetMapping(path = "/{id}")
    public Animal getAnimalById(@PathVariable("id") Integer id){
        return this.animalService.getAnimalById(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        animalService.addAnimal(animal);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAnimalById(@PathVariable("id") Integer id){
        animalService.deleteAnimalById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateAnimal(@PathVariable("id") Integer id, @RequestBody Animal animal){
        animalService.updateAnimal(id, animal);
    }
}