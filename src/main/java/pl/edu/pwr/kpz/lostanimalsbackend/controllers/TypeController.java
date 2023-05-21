package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.TypeService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Type;

import java.util.List;

@RestController
@RequestMapping("types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    public List<Type> getTypeList() {
        return typeService.list();
    }

    @GetMapping("{id}")
    public Type getTypeById(@PathVariable("id") int id) {
        return typeService.getOne(id);
    }

    @PostMapping
    public Type addType(@RequestBody Type type) {
        return typeService.add(type);
    }

    @PutMapping("{id}")
    public void updateType(@PathVariable("id") int id, @RequestBody Type type){
        typeService.update(id, type);
    }

    @DeleteMapping("{id}")
    public void deleteTypeById(@PathVariable("id") int id) {
        typeService.delete(id);
    }

    @GetMapping("{id}/breeds")
    public List<Breed> getBreedsOfType(@PathVariable(name = "id") int typeId) {
        return typeService.getBreedsOfType(typeId);
    }

    @PostMapping("{id}/breeds")
    public Breed addBreedOfType(@PathVariable("id") int typeId, @RequestBody Breed breed) {
        return typeService.addNewBreedOfType(typeId, breed);
    }

    @PutMapping("{id}/breeds/{breedId}")
    public Breed updateBreedOfType(@PathVariable("id") int typeId, @PathVariable int breedId, @RequestBody Breed breed) {
        return typeService.updateBreed(typeId, breed);
    }

    @DeleteMapping("{id}/breeds/{pictureId}")
    public void deleteAnimalBreed(@PathVariable("id") int typeId, @PathVariable("pictureId") int pictureId) {
        typeService.deleteBreedOfType(typeId, pictureId);
    }
}
