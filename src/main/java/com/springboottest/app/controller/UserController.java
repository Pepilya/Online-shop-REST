package com.springboottest.app.controller;

import com.springboottest.app.model.User;
import com.springboottest.app.service.ServDao;

import java.util.List;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    private ServDao service;

    public UserController() {
    }

    @Autowired
    public void setService(ServDao service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/app")
    @ApiOperation(value = "View a list of available users", response = User.class)
    List<User> getUsersList() {
        List<User> lst = service.getAllUsers();
        System.out.println(lst);
        return lst;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/app/{id}")
    @ApiOperation(value = "View a specific user", response = User.class)
    User getUser(@PathVariable String id) {
        int i = Integer.valueOf(id);
        return service.getUser(i);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/app")
    @ApiOperation(value = "Create a specific user", response = Integer.class)
    int addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/app/{id}")
    @ApiOperation(value = "Update a specific user", response = Integer.class)
    int updateUser(@PathVariable String id, @RequestBody User user) {
        int result = 0;
        int i = Integer.valueOf(id);
        User other = service.getUser(i);
        if (other != null)
            result = service.update(user, i);
        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/app/{id}")
    @ApiOperation(value = "Delete a specific user", response = Integer.class)
    int deleteUser(@PathVariable String id) {
        int i = Integer.valueOf(id);
        return service.delete(i);
    }
}