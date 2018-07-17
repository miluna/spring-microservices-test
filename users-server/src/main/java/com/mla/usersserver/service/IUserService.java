package com.mla.usersserver.service;


import com.mla.usersserver.api.dto.UserDTO;
import java.util.List;

public interface IUserService {

    UserDTO createUser(String name, String password, String email);

    UserDTO getUser(String name, String password);

    UserDTO getUser(Long id);

    UserDTO deleteUser(String name);

    UserDTO updateUserName(String name);

    UserDTO updateUserPassword(String password);

    List<UserDTO> getUsers();

}
