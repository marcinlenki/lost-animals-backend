package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.UserService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.User;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUserList(){
        return this.userService.getUserList();
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return this.userService.getUserById(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user){
        userService.updateUser(id, user);
    }

}
