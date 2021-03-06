package com.orest.rest_recap.user;

import com.orest.rest_recap.Bean;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;


import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserResource {

    private UserDaoService userDaoService;
    private MessageSource messageSource;

    public UserResource(UserDaoService userDaoService, MessageSource messageSource) {
        this.userDaoService = userDaoService;
        this.messageSource = messageSource;
    }

/*    @GetMapping("/hello")
    public String goodMorning(@RequestHeader(name = "Accept-Language",
            required = false) Locale locale) {

  *//*      return messageSource.getMessage("good.morning.message",
                null, locale);*//*

    }*/


    @GetMapping("/hello")
    public String goodMorning() {

  /*      return messageSource.getMessage("good.morning.message",
                null, locale);*/
        return messageSource.getMessage("good.morning.message",
                null, LocaleContextHolder.getLocale());
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























