package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.Map;

/**
 * Base interface for a crud service.
 * @param <U> entity type used for creating a request
 * @param <D> returned entity type
 */
@Validated
public interface CrudService <U, D> {
    Page<D> list(Map<String, String> params, Pageable pageable);

    default Page<D> list(Pageable pageable) {
        return list(Collections.emptyMap(), pageable);
    }

    D getOne(int id) throws RuntimeException;

    D add(U u) throws RuntimeException;

    D update(@Min(value = 1, message = "Main id must be over 0") int id, U u) throws RuntimeException;

    void delete(int id);
}
