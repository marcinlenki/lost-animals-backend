package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.LostReportRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.LostReportResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.LostReportDTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;

@Service
public class LostReportService extends MappedCrudService<LostReport, LostReportRequestDTO, LostReportResponseDTO> {
    public LostReportService(BaseRepository<LostReport> repository, LostReportDTOMapper mapper) {
        super(repository, LoggerFactory.getLogger(LostReportService.class), mapper);
    }
}
