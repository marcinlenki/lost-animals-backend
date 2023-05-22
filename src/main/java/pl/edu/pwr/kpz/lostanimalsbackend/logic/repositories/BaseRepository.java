package pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused") // future feature
@NoRepositoryBean
public interface BaseRepository <T> extends JpaRepository<T, Integer> {
    default List<T> getByNaturalId(String keyValue, String ... keyName) {
        return Collections.emptyList();
    }

    default boolean supportsNaturalId() {
        return false;
    }
}
