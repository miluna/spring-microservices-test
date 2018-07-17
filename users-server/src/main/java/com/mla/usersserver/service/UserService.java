package com.mla.usersserver.service;


import com.mla.usersserver.api.dto.UserDTO;
import com.mla.usersserver.entities.UserEntity;
import com.mla.usersserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        user.setPassword(password);
        user.setEmail(email);

        UserEntity result = userRepository.save(user);
        return new UserDTO(result.getId(), result.getUsername(), result.getPassword(), result.getEmail());
    }

    @Override
    public UserDTO deleteUser(String name) {

        return null;
    }

    @Override
    public UserDTO updateUserName(String name) {
        return null;
    }

    @Override
    public UserDTO updateUserPassword(String password) {
        return null;
    }

    @Override
    public UserDTO getUser(String name, String password) {
        // HASH THE PASSWORD
        UserEntity userEntity = userRepository.findUserEntityByUserAndPass(name, password);


        return null;
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
//        List<UserEntity> list =
        return null;
    }
}
