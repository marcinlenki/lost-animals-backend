package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.AnimalColorService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalColor;

import java.util.List;

@RestController
@RequestMapping("animal-colors")
@RequiredArgsConstructor
public class AnimalColorController {
    private final AnimalColorService animalColorService;

    @GetMapping
    public List<AnimalColor> getAnimalColorList(){
        return this.animalColorService.getAnimalColorList();
    }

    @GetMapping(path = "/{id}")
    public AnimalColor getAnimalColorById(@PathVariable("id") Integer id){
        return this.animalColorService.getAnimalColorById(id);
    }

    @PostMapping
    public void addAnimalColor(@RequestBody AnimalColor animalColor){
        animalColorService.addAnimalColor(animalColor);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAnimalColorById(@PathVariable("id") Integer id){
        animalColorService.deleteAnimalColorById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateAnimalColor(@PathVariable("id") Integer id, @RequestBody AnimalColor animalColor){
        animalColorService.updateAnimalColor(id, animalColor);
    }
}
