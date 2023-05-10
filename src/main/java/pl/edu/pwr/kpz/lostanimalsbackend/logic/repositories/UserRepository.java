package pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
