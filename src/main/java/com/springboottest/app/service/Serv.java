package com.springboottest.app.service;

import com.springboottest.app.model.User;

import java.util.List;

public interface Serv {
    List<User> getAllUsers();
    User getUser(int id);
    User addUser(User user);
    int delete(int id);
    User update(User user, int id);
}
