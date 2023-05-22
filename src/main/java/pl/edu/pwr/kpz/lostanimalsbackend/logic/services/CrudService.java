package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Base interface for a crud service.
 * @param <U> entity type used for creating a request
 * @param <D> returned entity type
 */
@Validated
public interface CrudService <U, D> {
    List<D> list(Map<String, String> params);

    default List<D> list() {
        return list(Collections.emptyMap());
    }

    D getOne(int id) throws RuntimeException;

    D add(U u) throws RuntimeException;

    D update(@Min(value = 1, message = "Main id must be over 0") int id, U u) throws RuntimeException;

    void delete(int id);
}
