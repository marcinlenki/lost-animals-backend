package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.UserService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.UserResponseDTO;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponseDTO> getUserList(Pageable pageable){
        return userService.list(pageable);
    }

    @GetMapping("{id}")
    public UserResponseDTO getUserById(@PathVariable("id") int id) {
        return userService.getOne(id);
    }

    @PostMapping
    public UserResponseDTO addUser(@Valid @RequestBody UserRequestDTO user) {
        return userService.add(user);
    }

    @PutMapping("{id}")
    public UserResponseDTO updateUser(@Valid @PathVariable("id") int id, @RequestBody UserRequestDTO user) {
        return userService.update(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        userService.delete(id);
    }
}
