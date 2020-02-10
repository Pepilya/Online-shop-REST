package com.springboottest.app.repo;

import com.springboottest.app.model.User;

import java.util.List;

public interface Dao {
    public List<User> getAllUsers();
    public User getUser(int id);
    public int addUser(User user);
    public int delete(int id);
    public int update(User user, int id);
}
