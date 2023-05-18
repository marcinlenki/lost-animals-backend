package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.UserRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements ConvertRequestDTO<User, UserResponseDTO> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<User> getUserList(){
        return this.userRepository.findAll();
    }

    public User getUserById(Integer id){
        return this.userRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "user with id: " + id + " dose not exists"
                ));
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteUserById(Integer id){
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("user with id:" + id + " dose not exists");
        }
        this.userRepository.deleteById(id);
    }

    public void updateUser(Integer id, User user){
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("user with id:" + id + " dose not exists");
        }
        this.userRepository.save(user);
    }

    @Override
    public User convertDtoToEmptyEntity(UserResponseDTO dto) {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper.map(dto, User.class);
    }

    @Override
    public User convertDtoToFullEntity(UserResponseDTO dto) {
        return null;
    }
}
