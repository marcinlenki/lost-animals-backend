package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.AnimalColorService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalColor;

@RestController
@RequestMapping("animal-colors")
@RequiredArgsConstructor
public class AnimalColorController {
    private final AnimalColorService animalColorService;

    @GetMapping
    public Page<AnimalColor> getAnimalColorList(Pageable pageable) {
        return animalColorService.list(pageable);
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
