package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.PictureService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;

@RestController
@RequestMapping("pictures")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;


    @GetMapping(path = "/{id}")
    public AnimalPicture getPictureById(@PathVariable("id") Integer id) {
        return this.pictureService.getPictureById(id);
    }

    @PostMapping
    public void addPicture(@RequestBody AnimalPicture animalPicture){
        pictureService.addPicture(animalPicture);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePictureById(@PathVariable("id") Integer id) throws Exception{
        pictureService.deletePictureById(id);
    }

    @PostMapping("test")
    public void test(@RequestParam("file") MultipartFile multipartFile) {
        pictureService.saveAsync(multipartFile, 1);
    }
}
