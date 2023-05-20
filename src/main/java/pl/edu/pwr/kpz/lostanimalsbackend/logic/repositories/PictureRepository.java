package pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<AnimalPicture,Integer> {
    List<AnimalPicture> findByAnimal_Id(int id);

}
