package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.PictureService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Picture;

import java.util.List;

@RestController
@RequestMapping("pictures")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @GetMapping
    public List<Picture> getPictureList(){
        return this.pictureService.getPictureList();
    }

    @GetMapping(path = "/{id}")
    public Picture getPictureById(@PathVariable("id") Integer id){
        return this.pictureService.getPictureById(id);
    }

    @PostMapping
    public void addPicture(@RequestBody Picture picture){
        pictureService.addPicture(picture);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePictureById(@PathVariable("id") Integer id){
        pictureService.deletePictureById(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePicture(@PathVariable("id") Integer id, @RequestBody Picture picture){
        pictureService.updatePicture(id, picture);
    }
}
