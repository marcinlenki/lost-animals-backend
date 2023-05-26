package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.BreedService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;


@RestController
@RequestMapping("breeds")
@RequiredArgsConstructor
public class BreedController {
    private final BreedService breedService;

    @GetMapping
    public Page<Breed> getBreedList(Pageable pageable) {
        return breedService.list(pageable);
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
