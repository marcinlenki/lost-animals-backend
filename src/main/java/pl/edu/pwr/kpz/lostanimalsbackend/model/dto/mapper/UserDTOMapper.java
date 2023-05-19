package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;

@Component
public class UserDTOMapper extends DTOMapper<User, UserResponseDTO> {
    public UserDTOMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public User convertDtoToEmptyEntity(UserResponseDTO dto) {
        return this.modelMapper.map(dto, User.class);
    }

    @Override
    public User convertDtoToFullEntity(UserResponseDTO dto) {
        return convertDtoToEmptyEntity(dto);
    }
}
