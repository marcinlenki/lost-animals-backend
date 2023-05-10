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
    public List<Breed> getBreedList(){
        return this.breedService.getBreedList();
    }

    @GetMapping(path = "/{id}")
    public Breed getBreedById(@PathVariable("id") Integer id){
        return this.breedService.getBreedById(id);
    }

    @PostMapping
    public void addBreed(@RequestBody Breed breed){
        breedService.addBreed(breed);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBreedById(@PathVariable("id") Integer id){
        breedService.deleteBreedById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateBreed(@PathVariable("id") Integer id, @RequestBody Breed breed){
        breedService.updateBreed(id, breed);
    }
}
