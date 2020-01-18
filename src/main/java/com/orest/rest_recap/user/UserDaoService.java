package com.orest.rest_recap.user;


import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {


    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1,"Adam", new Date()));
        users.add(new User(2,"John",new Date()));
        users.add(new User(3,"Peter", new Date()));
    }


    public List<User> findAll() {
        return users;

    }

    public User saveUser(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
      users.add(user);
        return user;
    }

    public User findUser(int id) {


        for (User user : users) {
            if (user.getId() == id) {
              return user;
            }
        }
        return null;
    }

    public User createUser(User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        User newUser = new User();
        newUser.setId(++usersCount);
        newUser.setName(user.getName());
        newUser.setBirthDate(new Date());

        user = saveUser(newUser);
        return user;


    }


}












