package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

/**
 *
 * @param <T> Entity
 * @param <U> DTO
 */
public interface MapperRequestDTO<T,U> {
    T convertDtoToEmptyEntity(U dto);
    T convertDtoToFullEntity(U dto);
}
