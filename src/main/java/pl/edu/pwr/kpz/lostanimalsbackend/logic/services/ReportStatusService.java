package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.ReportStatus;

@Service
public class ReportStatusService extends SimpleCrudService<ReportStatus> {

    public ReportStatusService(BaseRepository<ReportStatus> repository) {
        super(repository, LoggerFactory.getLogger(ReportStatusService.class));
    }

}
