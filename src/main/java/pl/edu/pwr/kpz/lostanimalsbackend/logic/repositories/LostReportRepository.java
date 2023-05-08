package pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;

public interface LostReportRepository extends JpaRepository<LostReport, Integer> {
}
