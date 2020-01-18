package com.orest.rest_recap;

import com.orest.rest_recap.user.User;
import com.orest.rest_recap.user.UserDaoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Hello {

    @Autowired
    UserDaoService userDaoService;


    @GetMapping("/hello/{name}")
    public Bean hello(@PathVariable String name) {
        return new Bean(String.format("Hello man, %s", name));
    }

    @GetMapping("/hello/all")
    List<User> allUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/user/{id}")
    public User foundUser(@PathVariable int id) throws NotFoundException {
        return userDaoService.findUser(id);
    }


}
