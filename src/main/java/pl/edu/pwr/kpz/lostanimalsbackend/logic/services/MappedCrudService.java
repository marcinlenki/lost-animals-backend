package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.DTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.DatabaseEntity;

import java.util.List;
import java.util.Map;

/**
 * This is an abstract class for CRUD services which require DTO mapping.
 * It uses {@link DTOMapper} to map requests and responses from given domain entity.
 *
 * @param <T> the domain type the service manage. Extends {@link pl.edu.pwr.kpz.lostanimalsbackend.model.entities.DatabaseEntity} class.
 * @param <U> the request type which is mapped to the domain type.
 * @param <D> the response type which is created from the domain type.
 */
public abstract class MappedCrudService<T extends DatabaseEntity, U, D>
        extends AbstractCrudService<T, U, D>
{

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
                    var e = new EntityNotFoundException("Entity of id " + id + " not found!");
                    logger.error(e.getMessage(), e);
                    return e;
                });

        return mapper.convertEntityToDTO(entity);
    }

    @Override
    public D add(U u) throws RuntimeException {
        var entity = mapper.convertDtoToFullEntity(u);
        entity.setId(0);
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
        entity.setId(id);
        return mapper.convertEntityToDTO(repository.save(entity));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
