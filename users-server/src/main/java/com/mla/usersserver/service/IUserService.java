package com.mla.usersserver.service;


import com.mla.usersserver.api.dto.UserDTO;
import java.util.List;

public interface IUserService {

    UserDTO createUser(String name, String password, String email);

    UserDTO getUser(String name, String password);

    UserDTO getUser(Long id);

    UserDTO deleteUser(Long id);

    UserDTO updateUserName(Long id, String name);

    UserDTO updateUserPassword(Long id, String password);

    List<UserDTO> getUsers();

}
