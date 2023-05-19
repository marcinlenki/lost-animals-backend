package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.UserRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.SeenReport;

@Component
public class SeenReportDTOMapper extends DTOMapper<SeenReport, SeenReportResponseDTO, SeenReportRequestDTO> {
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    public SeenReportDTOMapper(ModelMapper modelMapper, AnimalRepository animalRepository, UserRepository userRepository) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public SeenReport convertDtoToEmptyEntity(SeenReportResponseDTO dto) {
        return this.modelMapper.map(dto, SeenReport.class);
    }

    @Override
    public SeenReport convertDtoToFullEntity(SeenReportResponseDTO dto) {
        SeenReport seenReport = convertDtoToEmptyEntity(dto);
        seenReport.setUser(
                userRepository.findById(seenReport.getUser().getId())
                        .orElseThrow(()-> new IllegalStateException(
                                "user with id: " + seenReport.getUser().getId() + " dose not exists"
                        )));
        seenReport.setAnimal(
                animalRepository.findById(seenReport.getAnimal().getId())
                        .orElseThrow(()-> new IllegalStateException(
                                        "animal with id: " + seenReport.getAnimal().getId() + " dose not exists"
                                )));
        return seenReport;
    }

    @Override
    public SeenReportRequestDTO convertEntityToDTO(SeenReport entity) {
        return this.modelMapper.map(entity, SeenReportRequestDTO.class);
    }
}
