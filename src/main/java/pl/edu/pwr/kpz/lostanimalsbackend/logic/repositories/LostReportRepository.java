package pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;

@SuppressWarnings("unused")
@Repository
public interface LostReportRepository extends JpaRepository<LostReport, Integer> {
}
