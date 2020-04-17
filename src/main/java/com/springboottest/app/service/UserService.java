package com.springboottest.app.service;

import com.springboottest.app.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User addUser(User user, List <Integer> roles);

    User update(User user, int id, List<Integer> roles);

    Integer delete(int id);
}
