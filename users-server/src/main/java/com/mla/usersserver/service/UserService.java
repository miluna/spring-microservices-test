package com.mla.usersserver.service;


import com.mla.usersserver.api.dto.UserDTO;
import com.mla.usersserver.entities.UserEntity;
import com.mla.usersserver.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("UserService")
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(String name, String password, String email) {
        UserEntity user = new UserEntity();
        user.setUsername(name);
        // HASH THE PASSWORD
        String hash = new BCryptPasswordEncoder().encode(password);

        user.setPassword(hash);
        user.setEmail(email);

        UserEntity result = userRepository.save(user);
        return new UserDTO(result.getId(), result.getUsername(), result.getPassword(), result.getEmail());
    }

    @Override
    public UserDTO deleteUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()){
            userRepository.delete(user.get());
        }

        UserDTO erased = new UserDTO();
        erased.setId(id);

        return erased;
    }

    @Override
    public UserDTO updateUserName(Long id, String name) {
        Optional<UserEntity> user = userRepository.findById(id);

        UserDTO userDTO = null;
        if (user.isPresent()){
            user.get().setUsername(name);

            userDTO = new UserDTO(
                    user.get().getId(),
                    user.get().getUsername(),
                    user.get().getPassword(),
                    user.get().getEmail()
            );
        }

        return userDTO;
    }

    @Override
    public UserDTO updateUserPassword(Long id, String password) {
        Optional<UserEntity> user = userRepository.findById(id);

        UserDTO userDTO = null;
        if (user.isPresent()){
            String hash = new BCryptPasswordEncoder().encode(password);
            user.get().setPassword(hash);

            userDTO = new UserDTO(
                    user.get().getId(),
                    user.get().getUsername(),
                    user.get().getPassword(),
                    user.get().getEmail()
            );
        }

        return userDTO;
    }

    @Override
    public UserDTO getUser(String name, String password) {
        // HASH THE PASSWORD
        String hash = new BCryptPasswordEncoder().encode(password);
//        Optional<UserEntity> user = userRepository.findUserEntityByUserAndPass(name, hash);
        Optional<UserEntity> user = null;

        UserDTO userDTO = null;
        if (user.isPresent()){
            userDTO = new UserDTO(
                    user.get().getId(),
                    user.get().getUsername(),
                    user.get().getPassword(),
                    user.get().getEmail()
            );
        }

        return userDTO;
    }

    @Override
    public UserDTO getUser(Long id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        UserDTO userDTO = null;

        if (optional.isPresent()){
            userDTO = new UserDTO(
                    optional.get().getId(),
                    optional.get().getUsername(),
                    optional.get().getPassword(),
                    optional.get().getEmail());
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> list = userRepository.findAll();

        List<UserDTO> returnedList = new ArrayList<>();
        list.forEach(userEntity -> returnedList.add(new UserDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEmail())));

        return returnedList;
    }
}
