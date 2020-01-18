package com.orest.rest_recap.user;

import com.orest.rest_recap.Bean;
import javassist.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }


    @GetMapping("/hello/{name}")
    public Bean hello(@PathVariable String name) {
        return new Bean(String.format("Hello man, %s", name));
    }

    @GetMapping("/users/all")
    List<User> allUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/user/{id}")
    public User foundUser(@PathVariable int id) throws NotFoundException {
        return userDaoService.findUser(id);
    }

    // CREATED
    // input - details of users
    // output - CREATED & Return the created URI

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
       User savedUser =  userDaoService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

       return ResponseEntity.created(location).build();

    }
}























