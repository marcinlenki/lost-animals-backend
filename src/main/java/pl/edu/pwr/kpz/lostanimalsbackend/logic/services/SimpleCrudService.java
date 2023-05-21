package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Identifiable;

import java.util.List;
import java.util.Map;

public abstract class SimpleCrudService<T extends Identifiable> extends AbstractCrudService<T, T, T> {

    public SimpleCrudService(BaseRepository<T> repository, Logger logger) {
        super(repository, logger);
    }

    @Override
    public List<T> list(Map<String, String> params) {
        return repository.findAll();
    }

    @Override
    public T getOne(int id) throws RuntimeException {
        return repository
                .findById(id)
                .orElseThrow(() -> {
                    var e = new IllegalArgumentException("Entity id mustn't be set!");
                    logger.error(e.getMessage(), e);
                    return e;
                });
    }

    @Override
    public T add(T t) throws RuntimeException {
        t.setNewIdentity(0);
        return repository.save(t);
    }

    @Override
    public T update(int id, T t) throws RuntimeException {
        if (!repository.existsById(id)) {
            var e = new EntityNotFoundException("Entity " + t.getClass().getSimpleName() + " of id " + id + " doesn't exist!");
            logger.error(e.getMessage(), e);
            throw e;
        }

        t.setNewIdentity(id);
        return repository.save(t);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
