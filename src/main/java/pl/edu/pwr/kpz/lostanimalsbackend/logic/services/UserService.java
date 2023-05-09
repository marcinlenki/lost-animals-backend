package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.UserRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
        userRepository.deleteById(id);
    }

    //TODO write update
    public void updateUser(Integer id){
        User user = this.userRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "user with id: " + id + " dose not exists"
                ));


    }
}
