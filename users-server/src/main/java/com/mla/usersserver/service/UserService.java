package com.mla.usersserver.service;


import com.mla.usersserver.api.dto.UserDTO;
import com.mla.usersserver.entities.UserEntity;
import com.mla.usersserver.feign.IProductFeign;
import com.mla.usersserver.repositories.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("UserService")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IProductFeign productFeign;

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
    @HystrixCommand(fallbackMethod = "")
    public UserDTO deleteUser(Long id) {
        userRepository.deleteById(id);

        // USING REST TEMPLATE
//        new RestTemplate()
//                .delete("http://localhost:8765/ms-products/product/{id}", id);
        productFeign.deleteProductByUserId(id);
        return new UserDTO();
    }

    public UserDTO deleteUserDefault(Long id){
        System.out.println("Error during user delete");
        return null;
    }

    @Override
    public UserDTO updateUserName(Long id, String name) {
        Optional<UserEntity> user = userRepository.findById(id);

        UserDTO userDTO = null;
        if (user.isPresent()) {
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
        if (user.isPresent()) {
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
        Optional<UserEntity> user = userRepository.findUserEntityByUserAndPass(name, hash);

        UserDTO userDTO = null;
        if (user.isPresent()) {
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

        if (optional.isPresent()) {
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
