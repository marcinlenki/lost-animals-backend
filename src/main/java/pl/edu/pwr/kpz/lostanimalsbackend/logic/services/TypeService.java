package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Type;

import java.util.List;

@Service
public class TypeService extends SimpleCrudService<Type> {
    private final BreedService breedService;

    public TypeService(BaseRepository<Type> repository, BreedService breedService) {
        super(repository, LoggerFactory.getLogger(TypeService.class));
        this.breedService = breedService;
    }

    public List<Breed> getBreedsOfType(int typeId) {
        if (!repository.existsById(typeId))
            throw new EntityNotFoundException();

        return breedService.getBreedsByTypeId(typeId);
    }

    public Breed addNewBreedOfType(int typeId, Breed breed) throws RuntimeException {
        var type = getOne(typeId);
        breed.setType(type);
        return breedService.add(breed);
    }
}
