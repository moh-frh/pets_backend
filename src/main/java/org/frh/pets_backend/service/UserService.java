package org.frh.pets_backend.service;

import org.frh.pets_backend.dto.UserDTO;
import org.frh.pets_backend.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public List<UserDTO> listUsers();
    public UserDTO getUserById(Long id) throws UserNotFoundException;

    UserDTO saveUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException;

    void deleteUser(Long id);
}
