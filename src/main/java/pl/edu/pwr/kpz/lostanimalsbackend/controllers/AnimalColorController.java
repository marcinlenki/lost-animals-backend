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
    public List<AnimalColor> getAnimalColorList() {
        return animalColorService.list();
    }

    @GetMapping("{id}")
    public AnimalColor getAnimalColorById(@PathVariable("id") int id) {
        return animalColorService.getOne(id);
    }

    @PostMapping
    public AnimalColor addAnimalColor(@RequestBody AnimalColor animalColor) {
        return animalColorService.add(animalColor);
    }

    @PutMapping("{id}")
    public AnimalColor updateAnimalColor(@PathVariable("id") int id, @RequestBody AnimalColor animalColor) {
        return animalColorService.update(id, animalColor);
    }

    @DeleteMapping("{id}")
    public void deleteAnimalColorById(@PathVariable("id") int id) {
        animalColorService.delete(id);
    }
}
