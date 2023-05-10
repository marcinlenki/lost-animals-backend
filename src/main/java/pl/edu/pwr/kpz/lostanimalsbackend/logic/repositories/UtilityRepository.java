package pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;

// Animal entity is unrelated to the procedure goal but Spring requires some managed entity to be
// parameter of JpaRepository
@Repository
public interface UtilityRepository extends JpaRepository<Animal, Integer> {
    @Modifying
    @Query(value = "CALL seed_db();", nativeQuery = true)
    void resetDatabaseContents();
}
