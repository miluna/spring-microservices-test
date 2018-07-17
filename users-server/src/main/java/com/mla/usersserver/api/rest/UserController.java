package com.mla.usersserver.api.rest;

import com.mla.usersserver.api.dto.UserDTO;
import com.mla.usersserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResponseEntity<String> getStatus(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<URI> createUser(@RequestBody UserDTO userDTO){
        UserDTO result = userService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());

        return null;
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }
}
