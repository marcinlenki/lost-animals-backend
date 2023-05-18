package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

/**
 *
 * @param <T> Entity
 * @param <U> DTO
 */
public interface ConvertRequestDTO<T,U> {
    T convertDtoToEmptyEntity(U dto);
    T convertDtoToFullEntity(U dto);
}
