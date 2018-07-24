package com.mla.usersserver.api.rest;

import com.mla.usersserver.api.dto.UserDTO;
import com.mla.usersserver.entities.UserEntity;
import com.mla.usersserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("/")
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

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/user/{id}")
                .buildAndExpand(result.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        return ResponseEntity.ok(userService.getUser(Long.valueOf(id)));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
        UserDTO result = userService.deleteUser(id);

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/user/{name}/{password}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getRegisteredUser(@PathVariable String name, @PathVariable String password){
        UserDTO result = userService.getUser(name, password);
        return ResponseEntity.ok(result);
    }


}
