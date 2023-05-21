//package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.UserRepository;
//import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.UserDTOMapper;
//import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//    private final UserDTOMapper userDTOMapper;
//
//    public List<User> getUserList(){
//        return this.userRepository.findAll();
//    }
//
//    public User getUserById(Integer id){
//        return this.userRepository.findById(id)
//                .orElseThrow(()-> new IllegalStateException(
//                        "user with id: " + id + " dose not exists"
//                ));
//    }
//
//    public void addUser(User user){
//        userRepository.save(user);
//    }
//
//    public void deleteUserById(Integer id){
//        boolean exists = userRepository.existsById(id);
//        if(!exists){
//            throw new IllegalStateException("user with id:" + id + " dose not exists");
//        }
//        this.userRepository.deleteById(id);
//    }
//
//    public void updateUser(Integer id, User user){
//        boolean exists = userRepository.existsById(id);
//        if(!exists){
//            throw new IllegalStateException("user with id:" + id + " dose not exists");
//        }
//        this.userRepository.save(user);
//    }
//
//}


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
