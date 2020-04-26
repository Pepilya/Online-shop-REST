package com.springboottest.app.controllers;

import com.springboottest.app.model.Role;
import com.springboottest.app.model.CustomUser;
import com.springboottest.app.service.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import static com.springboottest.app.config.JwtFilter.AUTH_TOKEN;

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
    public ResponseEntity<List<CustomUser>> getUsersList(@RequestHeader(AUTH_TOKEN) String token) {
        List<CustomUser> list = adminService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<CustomUser> getUserById(  @RequestHeader(AUTH_TOKEN) String token,
                                                    @PathVariable int id) {
        CustomUser resultUser = adminService.getUserById(id);
        return ResponseEntity.ok(resultUser);
    }

    @PostMapping("/users")
    public ResponseEntity<CustomUser> addUser(@RequestHeader(AUTH_TOKEN) String token,
                                              @RequestBody @Valid CustomUser user,
                                              @RequestParam @Valid List<Integer> roles) {
        CustomUser resultUser = adminService.addUser(user, roles);
        return new ResponseEntity<CustomUser>(resultUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<CustomUser> updateUser(@RequestHeader(AUTH_TOKEN) String token,
                                                 @PathVariable int id,
                                                 @RequestBody CustomUser user,
                                                 @RequestParam(required = false) List<Integer> roles) {

        CustomUser resultUser = adminService.update(user, id, roles);
        return new ResponseEntity<CustomUser>(resultUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Integer> deleteUser(@RequestHeader(AUTH_TOKEN) String token,
                                              @PathVariable int id) {
        return ResponseEntity.ok(adminService.delete(id));
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleById(@RequestHeader(AUTH_TOKEN) String token,
                            @PathVariable int roleId) {
        return adminService.getRoleById(roleId);
    }

    @PostMapping("/role")
    public Role addRole(@RequestHeader(AUTH_TOKEN) String token,
                        @RequestBody Role role) {
        return adminService.addRole(role);
    }

    @DeleteMapping("/role/{roleId}")
    public Role deleteRole(@RequestHeader(AUTH_TOKEN) String token,
                           @PathVariable int roleId) {
        return adminService.deleteRole(roleId);
    }


}