package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Type> getTypeList(Pageable pageable) {
        return typeService.list(pageable);
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
}
