package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.UserRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.SeenReport;

@Component
public class SeenReportDTOMapper extends DTOMapper<SeenReport, SeenReportRequestDTO, SeenReportResponseDTO> {
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    public SeenReportDTOMapper(ModelMapper modelMapper, AnimalRepository animalRepository, UserRepository userRepository) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public SeenReport convertDtoToEmptyEntity(SeenReportRequestDTO dto) {
        return this.modelMapper.map(dto, SeenReport.class);
    }

    @Override
    public SeenReport convertDtoToFullEntity(SeenReportRequestDTO dto) {
        SeenReport seenReport = convertDtoToEmptyEntity(dto);
        seenReport.setUser(
                userRepository.findById(seenReport.getUser().getId())
                        .orElseThrow(()-> new IllegalStateException(
                                "user with id: " + seenReport.getUser().getId() + " dose not exists"
                        )));
        return seenReport;
    }

    @Override
    public SeenReportResponseDTO convertEntityToDTO(SeenReport entity) {
        return this.modelMapper.map(entity, SeenReportResponseDTO.class);
    }
}
