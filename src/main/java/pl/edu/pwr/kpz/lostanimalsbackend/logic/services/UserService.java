package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.UserDTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;

@Service
public class UserService extends MappedCrudService<User, UserRequestDTO, UserResponseDTO> {

    public UserService(BaseRepository<User> repository, UserDTOMapper mapper) {
        super(repository, LoggerFactory.getLogger(UserService.class), mapper);
    }
}
