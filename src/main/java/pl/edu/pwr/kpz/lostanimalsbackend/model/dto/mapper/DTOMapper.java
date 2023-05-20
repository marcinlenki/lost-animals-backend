package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class DTOMapper<T,U,D> implements MapperRequestDTO<T,U>, MapperResponseDTO<T,D> {
    final ModelMapper modelMapper;
}
