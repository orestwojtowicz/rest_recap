package com.orest.rest_recap.user;

import com.orest.rest_recap.Bean;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.core.TypeReferences;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public EntityModel<User> foundUser(@PathVariable int id)  {

            User foundUser = userDaoService.findUser(id);
            if (foundUser == null) {
                throw new UserNotFoundException("user with id - " + id + " not found");
            }

        EntityModel<User> model = new EntityModel<>(foundUser);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).allUsers());
        model.add(linkTo.withRel("all-users"));



                return model;
    }

    // CREATED
    // input - details of users
    // output - CREATED & Return the created URI

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
       User savedUser =  userDaoService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

       return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/delete/user/{id}")
    public void deleteUser(@PathVariable int id) {
            userDaoService.deleteUser(id);
    }

}























