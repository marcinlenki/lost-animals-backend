package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.ReportStatusRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.LostReportResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;

@Component
public class LostReportDTOMapper extends DTOMapper<LostReport, LostReportResponseDTO> {
    private final AnimalRepository animalRepository;
    private final ReportStatusRepository reportStatusRepository;

    public LostReportDTOMapper(ModelMapper modelMapper, AnimalRepository animalRepository, ReportStatusRepository reportStatusRepository) {
        super(modelMapper);
        this.animalRepository = animalRepository;
        this.reportStatusRepository = reportStatusRepository;
    }

    @Override
    public LostReport convertDtoToEmptyEntity(LostReportResponseDTO dto) {
        return this.modelMapper.map(dto, LostReport.class);
    }

    @Override
    public LostReport convertDtoToFullEntity(LostReportResponseDTO dto) {
        LostReport lostReport = convertDtoToEmptyEntity(dto);
        lostReport.setAnimal(
                animalRepository.findById(lostReport.getAnimal().getId())
                        .orElseThrow(()-> new IllegalStateException(
                                "animal with id: " + lostReport.getAnimal().getId() + " dose not exists"
                        )));
        lostReport.setStatus(
                reportStatusRepository.findById(lostReport.getStatus().getId())
                        .orElseThrow(()-> new IllegalStateException(
                                "status with id: " + lostReport.getStatus().getId() + " dose not exists"
                        )));
        return lostReport;
    }
}
