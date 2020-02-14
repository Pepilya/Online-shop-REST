package com.springboottest.app.controller;

import com.springboottest.app.model.User;
import com.springboottest.app.service.UserService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService service;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getUsersList() {
        LOGGER.info("Attempt to get all users");
        List<User> list = service.getAllUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<User> getUser(@PathVariable int id) {
        LOGGER.info("Attempt to get " + id + " users");
        User resultUser = service.getUser(id);
        return ResponseEntity.ok(resultUser);
    }

    @PostMapping("/users")
    ResponseEntity<User> addUser(@RequestBody User user) {
        LOGGER.info("Attempt to post " + user.getName() + " users");
        User resultUser = service.addUser(user);
        return new ResponseEntity<User>(resultUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        LOGGER.info("Attempt to update " + id + " users");

        User resultUser = service.update(user, id);
        return new ResponseEntity<User>(resultUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<User> deleteUser(@PathVariable int id) {
        LOGGER.info("Attempt to delete " + id + " users");
        User resultUser = service.delete(id);
        return ResponseEntity.ok(resultUser);
    }
}