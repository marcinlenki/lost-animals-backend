package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;

@Component
public class UserDTOMapper extends DTOMapper<User, UserRequestDTO, UserResponseDTO> {
    public UserDTOMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public User convertDtoToEmptyEntity(UserRequestDTO dto) {
        return this.modelMapper.map(dto, User.class);
    }

    @Override
    public User convertDtoToFullEntity(UserRequestDTO dto) {
        return convertDtoToEmptyEntity(dto);
    }

    @Override
    public UserResponseDTO convertEntityToDTO(User entity) {
        return this.modelMapper.map(entity, UserResponseDTO.class);
    }
}
