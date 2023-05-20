package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.AnimalService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;

import java.util.List;

@RestController
@RequestMapping("animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public List<Animal> getAnimalList() {
        return this.animalService.getAnimalList();
    }

    @GetMapping(path = "/{id}")
    public Animal getAnimalById(@PathVariable("id") Integer id){
        return this.animalService.getAnimalById(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        animalService.addAnimal(animal);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAnimalById(@PathVariable("id") Integer id){
        animalService.deleteAnimalById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateAnimal(@PathVariable("id") Integer id, @RequestBody Animal animal){
        animalService.updateAnimal(id, animal);
    }

    @GetMapping("{id}/pictures")
    public List<AnimalPicture> getAnimalPictures(@PathVariable(name = "id") Integer animalId) {
        return animalService.getAnimalPictures(animalId);
    }

    @PostMapping("{id}/pictures")
    public Object addAnimalPictures(@PathVariable("id") Integer animalId,
                                                    @RequestParam("files") MultipartFile[] files) {

        return animalService.uploadAnimalPictures(files, animalId);
    }

    @DeleteMapping("{id}/pictures/{pictureId}")
    public void deleteAnimalPicture(@PathVariable("id") Integer animalId,
                                    @PathVariable("pictureId") Integer pictureId) {

        animalService.deleteAnimalPicture(animalId, pictureId);
    }

    @DeleteMapping("{id}/pictures")
    public void deleteAnimalPictures(@PathVariable("id") Integer animalId,
                                    @RequestParam("ids") String pictureIds) {

        animalService.deleteAnimalPictures(animalId, pictureIds);
    }
}