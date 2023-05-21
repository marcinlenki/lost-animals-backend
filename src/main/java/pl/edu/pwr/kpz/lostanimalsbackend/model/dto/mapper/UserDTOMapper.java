package pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.RoleRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;

@Component
public class UserDTOMapper extends DTOMapper<User, UserRequestDTO, UserResponseDTO> {
    private final RoleRepository roleRepository;

    public UserDTOMapper(ModelMapper modelMapper, RoleRepository roleRepository) {
        super(modelMapper);
        this.roleRepository = roleRepository;
    }

    @Override
    public User convertDtoToEmptyEntity(UserRequestDTO dto) {
        return this.modelMapper.map(dto, User.class);
    }

    @Override
    public User convertDtoToFullEntity(UserRequestDTO dto) {
        User user =  convertDtoToEmptyEntity(dto);
        System.out.println("User from model mapper = " + user);

        user.setRole(roleRepository.findById(user.getRole().getId())
                .orElseThrow(()-> new IllegalStateException(
                        "user with id: " + user.getRole().getId() + " dose not exists"
                )));
        return user;
    }

    @Override
    public UserResponseDTO convertEntityToDTO(User entity) {
        return this.modelMapper.map(entity, UserResponseDTO.class);
    }
}
