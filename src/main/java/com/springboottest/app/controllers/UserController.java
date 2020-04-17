package com.springboottest.app.controllers;

import com.springboottest.app.model.User;
import com.springboottest.app.service.UserService;

import java.util.List;

import com.springboottest.app.validators.RoleValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsersList() {
        List<User> list = service.getAllUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User resultUser = service.getUserById(id);
        return ResponseEntity.ok(resultUser);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user,
                                        @RequestParam @Valid List<Integer> roles) {
        User resultUser = service.addUser(user, roles);
        return new ResponseEntity<User>(resultUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,
                                    @RequestBody User user,
                                    @RequestParam(required = false) List<Integer> roles) {

        User resultUser = service.update(user, id, roles);
        return new ResponseEntity<User>(resultUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}