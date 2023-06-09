package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.DatabaseEntity;

@Transactional
@RequiredArgsConstructor
public abstract class AbstractCrudService<T extends DatabaseEntity, U, D> implements CrudService<U, D> {

    protected final BaseRepository<T> repository;

    protected final Logger logger;

}
