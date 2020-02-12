package com.springboottest.app.controller;

import com.springboottest.app.model.User;
import com.springboottest.app.service.Serv;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private Serv service;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController() {
    }

    @Autowired
    public void setService(Serv service) {
        this.service = service;
    }

    @GetMapping("/app")
    List<User> getUsersList() {
        LOGGER.info("Attempt to get all users");

        List<User> lst = service.getAllUsers();
        return lst;
    }

    @GetMapping("/app/{id}")
    User getUser(@PathVariable String id) {
        LOGGER.info("Attempt to get " + id +  " users");

        int i = Integer.valueOf(id);
        return service.getUser(i);
    }

    @PostMapping("/app")
    User addUser(@RequestBody User user) {
        LOGGER.info("Attempt to post " + user.getName() + " users");

        service.addUser(user);
        return user;
    }

    @PutMapping("/app/{id}")
    User updateUser(@PathVariable String id, @RequestBody User user) {
        LOGGER.info("Attempt to update " + id + " users");

        int i = Integer.valueOf(id);
        User toChange = service.getUser(i);
        if (toChange != null)
            user = service.update(user, i);
        return user;
    }

    @DeleteMapping("/app/{id}")
    int deleteUser(@PathVariable String id) {
        LOGGER.info("Attempt to delete " + id + " users");

        int i = Integer.valueOf(id);
        return service.delete(i);
    }
}