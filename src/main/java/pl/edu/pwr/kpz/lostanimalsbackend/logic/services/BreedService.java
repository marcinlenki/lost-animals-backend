package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BreedRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;

import java.util.List;

@Service
public class BreedService extends SimpleCrudService<Breed> {

    public BreedService(BaseRepository<Breed> repository) {
        super(repository, LoggerFactory.getLogger(BreedService.class));
    }

    public List<Breed> getBreedsByTypeId(int typeId) {
        return ((BreedRepository) repository).findByType_Id(typeId);
    }

}
