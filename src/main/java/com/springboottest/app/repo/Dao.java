package com.springboottest.app.repo;

import com.springboottest.app.model.User;

import java.util.List;

public interface Dao {
    List<User> getAllUsers();

    User getUser(int id);

    User addUser(User user);

    User delete(int id);

    User update(User user, int id);
}
