package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.PictureService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;

import java.util.List;

@RestController
@RequestMapping("pictures")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @GetMapping
    public List<AnimalPicture> getPictureList(){
        return this.pictureService.getPictureList();
    }

    @GetMapping(path = "/{id}")
    public AnimalPicture getPictureById(@PathVariable("id") Integer id){
        return this.pictureService.getPictureById(id);
    }

    @PostMapping
    public void addPicture(@RequestBody AnimalPicture animalPicture){
        pictureService.addPicture(animalPicture);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePictureById(@PathVariable("id") Integer id){
        pictureService.deletePictureById(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePicture(@PathVariable("id") Integer id, @RequestBody AnimalPicture animalPicture){
        pictureService.updatePicture(id, animalPicture);
    }
}
