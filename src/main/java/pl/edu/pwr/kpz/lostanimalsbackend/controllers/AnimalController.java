package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.AnimalService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.AnimalRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.AnimalResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;

import java.util.List;

@RestController
@RequestMapping("animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public List<AnimalResponseDTO> getAnimalList() {
        return animalService.list();
    }

    @GetMapping("{id}")
    public AnimalResponseDTO getAnimalById(@PathVariable("id") int id) {
        return animalService.getOne(id);
    }

    @PostMapping
    public AnimalResponseDTO addAnimal(@RequestBody AnimalRequestDTO animal) {
        return animalService.add(animal);
    }

    @PutMapping("{id}")
    public AnimalResponseDTO updateAnimal(@PathVariable("id") int id, @RequestBody AnimalRequestDTO animal) {
        return animalService.update(id, animal);
    }

    @DeleteMapping("{id}")
    public void deleteAnimalById(@PathVariable("id") int id) {
        animalService.delete(id);
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
