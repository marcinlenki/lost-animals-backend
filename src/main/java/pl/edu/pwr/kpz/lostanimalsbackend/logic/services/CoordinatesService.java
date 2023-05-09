package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.CoordinatesRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinates;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;

    public List<Coordinates> getCoordinatesList(){
        return this.coordinatesRepository.findAll();
    }

    public Coordinates getCoordinatesById(Integer id){
        return this.coordinatesRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "coordinates with id: " + id + " dose not exists"
                ));
    }

    public void addCoordinates(Coordinates coordinates){
        this.coordinatesRepository.save(coordinates);
    }

    public void deleteCoordinates(Integer id){
        boolean exists = coordinatesRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("coordinates with id:" + id + " dose not exists");
        }
        coordinatesRepository.deleteById(id);
    }

    //TODO write update
    public void updateCoordinates(Integer id){
        Coordinates coordinates = this.coordinatesRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "coordinates with id: " + id + " dose not exists"
                ));
    }
}
