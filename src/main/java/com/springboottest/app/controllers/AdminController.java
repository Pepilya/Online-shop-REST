package com.springboottest.app.controllers;

import com.springboottest.app.model.Role;
import com.springboottest.app.model.User;
import com.springboottest.app.service.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService service) {
        this.adminService = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsersList() {
        List<User> list = adminService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User resultUser = adminService.getUserById(id);
        return ResponseEntity.ok(resultUser);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user,
                                        @RequestParam @Valid List<Integer> roles) {
        User resultUser = adminService.addUser(user, roles);
        return new ResponseEntity<User>(resultUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,
                                           @RequestBody User user,
                                           @RequestParam(required = false) List<Integer> roles) {

        User resultUser = adminService.update(user, id, roles);
        return new ResponseEntity<User>(resultUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int id) {
        return ResponseEntity.ok(adminService.delete(id));
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleById(@PathVariable int roleId) {
        return adminService.getRoleById(roleId);
    }

    @PostMapping("/role")
    public Role addRole(@RequestBody Role role) {
        return adminService.addRole(role);
    }

    @DeleteMapping("/role/{roleId}")
    public Role deleteRole(@PathVariable int roleId) {
        return adminService.deleteRole(roleId);
    }


}