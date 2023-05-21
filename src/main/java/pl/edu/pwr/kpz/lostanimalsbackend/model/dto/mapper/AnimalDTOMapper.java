package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.AnimalColorRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BreedRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.UserRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.AnimalResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.AnimalRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Sex;

@Component
public class AnimalDTOMapper extends DTOMapper<Animal, AnimalRequestDTO, AnimalResponseDTO> {
    private final UserRepository userRepository;
    private final AnimalColorRepository animalColorRepository;
    private final BreedRepository breedRepository;

    public AnimalDTOMapper(ModelMapper modelMapper, UserRepository userRepository, AnimalColorRepository animalColorRepository, BreedRepository breedRepository) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.animalColorRepository = animalColorRepository;
        this.breedRepository = breedRepository;
    }

    @Override
    public Animal convertDtoToEmptyEntity(AnimalRequestDTO dto) {
        return this.modelMapper.map(dto, Animal.class);
    }

    @Override
    public Animal convertDtoToFullEntity(AnimalRequestDTO dto) {
        Animal animal = convertDtoToEmptyEntity(dto);

        // set default sex
        if (animal.getSex() == null)
            animal.setSex(Sex.NIEZNANA);

        // Because of proper validation groups, animal id can be equal to 0 only when AnimalRequestDTO is passed from SeenReportDTOMapper.
        // When this happens, a new "temporary" Animal Entity is added to the database and the owner of this animal should be set to NULL.
        if (animal.getId() != 0) {
            animal.setOwner(
                    userRepository.findById(animal.getOwner().getId())
                            .orElseThrow(()-> new IllegalStateException(
                                    "user with id: " + animal.getOwner().getId() + " dose not exists"
                            )));
        }

        animal.setColor(
                animalColorRepository.findById(animal.getColor().getId())
                        .orElseThrow(()-> new IllegalStateException(
                                "animal color with id: " + animal.getColor().getId() + " dose not exists"
                        )));
        animal.setBreed(
                breedRepository.findById(animal.getBreed().getId())
                        .orElseThrow(()-> new IllegalStateException(
                                "breed with id: " + animal.getBreed().getId() + " dose not exists"
                        )));
        return animal;
    }

    @Override
    public AnimalResponseDTO convertEntityToDTO(Animal entity) {
        return this.modelMapper.map(entity, AnimalResponseDTO.class);
    }
}
