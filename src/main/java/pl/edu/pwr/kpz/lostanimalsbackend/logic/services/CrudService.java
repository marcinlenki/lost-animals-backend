package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Base interface for a crud service.
 * @param <U> entity type used for creating a request
 * @param <D> returned entity type
 */
public interface CrudService <U, D> {
    List<D> list(Map<String, String> params);

    default List<D> list() {
        return list(Collections.emptyMap());
    }

    D getOne(int id) throws RuntimeException;

    D add(U u) throws RuntimeException;

    D update(int id, U u) throws RuntimeException;

    void delete(int id);
}
