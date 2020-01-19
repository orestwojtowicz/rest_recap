package com.orest.rest_recap.user;

import com.orest.rest_recap.Bean;
import com.orest.rest_recap.repository.UserRepository;
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
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserRepository userRepository;
    private UserDaoService userDaoService;
    private MessageSource messageSource;

    public UserJpaResource(UserRepository userRepository, MessageSource messageSource, UserDaoService userDaoService) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.userDaoService = userDaoService;
    }

/*    @GetMapping("/hello")
    public String goodMorning(@RequestHeader(name = "Accept-Language",
            required = false) Locale locale) {

  *//*      return messageSource.getMessage("good.morning.message",
                null, locale);*//*

    }*/


    @GetMapping("/jpa/hello")
    public String goodMorning() {

  /*      return messageSource.getMessage("good.morning.message",
                null, locale);*/
        return messageSource.getMessage("good.morning.message",
                null, LocaleContextHolder.getLocale());
    }


    @GetMapping("/jpa/hello/{name}")
    public Bean hello(@PathVariable String name) {
        return new Bean(String.format("Hello man, %s", name));
    }

    @GetMapping("/jpa/users/all")
    List<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/user/{id}")
    public EntityModel<User> foundUser(@PathVariable int id)  {

            Optional<User> foundUser = userRepository.findById(id);
            if (!foundUser.isPresent()) {
                throw new UserNotFoundException("user with id - " + id + " not found");
            }

        EntityModel<User> model = new EntityModel<>(foundUser.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).allUsers());
        model.add(linkTo.withRel("all-users"));


                return model;
    }

    // CREATED
    // input - details of users
    // output - CREATED & Return the created URI

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
       User savedUser =  userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

       return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/jpa//delete/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }




    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllUsers(@PathVariable int id) {
       Optional<User> foindUser = userRepository.findById(id);

       if (!foindUser.isPresent()) throw new UserNotFoundException("id " + id);

       return foindUser.get().getPosts();

    }


}























