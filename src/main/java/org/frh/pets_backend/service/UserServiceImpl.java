package org.frh.pets_backend.service;

import lombok.extern.slf4j.Slf4j;
import org.frh.pets_backend.dto.UserDTO;
import org.frh.pets_backend.entity.User;
import org.frh.pets_backend.exception.UserNotFoundException;
import org.frh.pets_backend.mapper.UserMapperImpl;
import org.frh.pets_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    UserMapperImpl dtoMapperUser;
    public UserServiceImpl(UserRepository userRepository, UserMapperImpl dtoMapperUser){
        this.userRepository = userRepository;
        this.dtoMapperUser = dtoMapperUser;
    }
    @Override
    public List<UserDTO> listUsers(){
        List<User> listUsers = userRepository.findAll();
        List<UserDTO> listUserDTOS = new ArrayList<>();
        for(User user:listUsers){
            UserDTO userDTO = dtoMapperUser.fromUser(user);
            listUserDTOS.add(userDTO);
        }
        return listUserDTOS;
    }
    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found !!!!"));
        return dtoMapperUser.fromUser(user);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO){
        User user = dtoMapperUser.fromUserDTO(userDTO);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        User savedUser = userRepository.save(user);
        return dtoMapperUser.fromUser(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException {
        log.info("updating new user !!");
        User userInformation = userRepository.findById(userDTO.getId()).orElseThrow(()->new UserNotFoundException("user not found !!!"));
        User user = dtoMapperUser.fromUserDTO(userDTO);
        user.setCreatedAt(userInformation.getCreatedAt());
        user.setUpdatedAt(new Date());
        User savedUser = userRepository.save(user);
        return dtoMapperUser.fromUser(savedUser);
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
