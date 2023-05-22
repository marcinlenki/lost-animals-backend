package pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;

import java.util.List;

@Repository
public interface BreedRepository extends BaseRepository<Breed> {
    List<Breed> findByType_Id(int id);


}
