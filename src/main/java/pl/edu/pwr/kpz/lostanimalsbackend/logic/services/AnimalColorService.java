package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalColor;

@Service
public class AnimalColorService extends SimpleCrudService<AnimalColor> {

    public AnimalColorService(BaseRepository<AnimalColor> repository) {
        super(repository, LoggerFactory.getLogger(AnimalColorService.class));
    }

}
