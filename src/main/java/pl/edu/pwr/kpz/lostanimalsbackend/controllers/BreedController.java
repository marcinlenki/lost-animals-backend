package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.BreedService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;

import java.util.List;

@RestController
@RequestMapping("breeds")
@RequiredArgsConstructor
public class BreedController {
    private final BreedService breedService;

    @GetMapping
    public List<Breed> getBreedList() {
        return breedService.list();
    }

    @GetMapping("{id}")
    public Breed getBreedById(@PathVariable("id") int id) {
        return breedService.getOne(id);
    }

    @PostMapping
    public Breed addBreed(@RequestBody Breed breed) {
        return breedService.add(breed);
    }

    @PutMapping("{id}")
    public Breed updateBreed(@PathVariable("id") int id, @RequestBody Breed breed) {
        return breedService.update(id, breed);
    }

    @DeleteMapping("{id}")
    public void deleteBreedById(@PathVariable("id") int id) {
        breedService.delete(id);
    }
}
