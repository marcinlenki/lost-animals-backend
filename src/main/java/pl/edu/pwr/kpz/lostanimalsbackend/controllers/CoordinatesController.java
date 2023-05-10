package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.CoordinatesService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinates;

import java.util.List;

@RestController
@RequestMapping("coordinates")
@RequiredArgsConstructor
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    @GetMapping
    public List<Coordinates> getCoordinatesList(){
        return this.coordinatesService.getCoordinatesList();
    }

    @GetMapping(path = "/{id}")
    public Coordinates getCoordinatesById(@PathVariable("id") Integer id){
        return this.coordinatesService.getCoordinatesById(id);
    }

    @PostMapping
    public void addCoordinates(@RequestBody Coordinates coordinates){
        coordinatesService.addCoordinates(coordinates);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCoordinatesById(@PathVariable("id") Integer id){
        coordinatesService.deleteCoordinatesById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateCoordinates(@PathVariable("id") Integer id, @RequestBody Coordinates coordinates){
        coordinatesService.updateCoordinates(id, coordinates);
    }
}
