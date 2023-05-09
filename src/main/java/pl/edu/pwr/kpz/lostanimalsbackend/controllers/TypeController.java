package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.TypeService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Type;

import java.util.List;

@RestController
@RequestMapping("types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    public List<Type> getTypeList(){
        return this.typeService.getTypeList();
    }

    @GetMapping(path = "/{id}")
    public Type getTypeById(@PathVariable("id") Integer id){
        return this.typeService.getTypeById(id);
    }

    @PostMapping
    public void addType(@RequestBody Type type){
        typeService.addType(type);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTypeById(@PathVariable("id") Integer id){
        typeService.deleteTypeById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateType(@PathVariable("id") Integer id, @RequestBody Type type){
        typeService.updateType(id, type);
    }
}
