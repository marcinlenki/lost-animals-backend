package pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.ReportStatus;

@Repository
public interface ReportStatusRepository extends JpaRepository<ReportStatus,Integer> {
}
