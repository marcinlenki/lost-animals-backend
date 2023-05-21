package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.AnimalDTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.SeenReportDTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.SeenReport;

@Service
public class SeenReportService extends MappedCrudService<SeenReport, SeenReportRequestDTO, SeenReportResponseDTO> {
    private final AnimalDTOMapper animalDTOMapper;
    private final AnimalService animalService;


    public SeenReportService(BaseRepository<SeenReport> repository, SeenReportDTOMapper mapper,
                             AnimalDTOMapper animalDTOMapper, AnimalService animalService) {

        super(repository, LoggerFactory.getLogger(SeenReportService.class), mapper);
        this.animalDTOMapper = animalDTOMapper;
        this.animalService = animalService;
    }

    @Override
    public SeenReportResponseDTO add(SeenReportRequestDTO seenReportRequestDTO) throws RuntimeException {
        var entity = mapper.convertDtoToFullEntity(seenReportRequestDTO);
        entity.setId(0);

        // if animalId is null than at this point AnimalRequestDTO must have been passed
        if (seenReportRequestDTO.getAnimalId() == null) {

            // save new animal without an owner
            var mappedAnimal = animalDTOMapper.convertDtoToFullEntity(seenReportRequestDTO.getAnimal());
            mappedAnimal.setOwner(null);

            var newAnimal = animalService.addSimple(mappedAnimal);
            entity.setAnimal(newAnimal);
        }

        return mapper.convertEntityToDTO(repository.save(entity));
    }
}
