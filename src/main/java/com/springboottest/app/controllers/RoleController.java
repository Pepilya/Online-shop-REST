package com.springboottest.app.controllers;

import com.springboottest.app.model.Role;
import com.springboottest.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleById(@PathVariable int roleId) {
        return roleService.getRoleById(roleId);
    }

    @PostMapping("/role")
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @DeleteMapping("/role/{roleId}")
    public Role deleteRole(@PathVariable int roleId) {
        return roleService.deleteRole(roleId);
    }
}
