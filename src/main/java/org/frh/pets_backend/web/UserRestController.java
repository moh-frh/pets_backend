package org.frh.pets_backend.web;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.UserDTO;
import org.frh.pets_backend.entity.User;
import org.frh.pets_backend.exception.UserNotFoundException;
import org.frh.pets_backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserRestController {
    private UserService userService;
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> listUsers(){
        return userService.listUsers();
    }
    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable(name = "id") Long userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable(name = "id") Long userId, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        userDTO.setId(userId);
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(name = "id") Long userID){
        userService.deleteUser(userID);
    }
}
