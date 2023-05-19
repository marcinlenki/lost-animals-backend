package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class DTOMapper<U,T> implements MapperRequestDTO<U,T> {
    final ModelMapper modelMapper;
}
