package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.DTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Identifiable;

import java.util.List;
import java.util.Map;

public abstract class MappedCrudService<T extends Identifiable, U, D> extends AbstractCrudService<T, U, D> {
    protected final DTOMapper<T, U, D> mapper;

    public MappedCrudService(BaseRepository<T> repository, Logger logger, DTOMapper<T, U, D> mapper) {
        super(repository, logger);
        this.mapper = mapper;
    }


    @Override
    public List<D> list(Map<String, String> params) {
        return repository.findAll()
                .stream()
                .map(mapper::convertEntityToDTO)
                .toList();
    }

    @Override
    public D getOne(int id) throws RuntimeException {
        var entity = repository
                .findById(id)
                .orElseThrow(() -> {
                    var e = new IllegalArgumentException("Entity id mustn't be set!");
                    logger.error(e.getMessage(), e);
                    return e;
                });

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public D add(U u) throws RuntimeException {
        var entity = mapper.convertDtoToFullEntity(u);
        return mapper.convertEntityToDTO(repository.save(entity));
    }

    @Override
    public D update(int id, U u) throws RuntimeException {
        if (!repository.existsById(id)) {
            var e = new EntityNotFoundException("Entity of id " + id + " doesn't exist!");
            logger.error(e.getMessage(), e);
            throw e;
        }

        var entity = mapper.convertDtoToFullEntity(u);
        return mapper.convertEntityToDTO(repository.save(entity));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
