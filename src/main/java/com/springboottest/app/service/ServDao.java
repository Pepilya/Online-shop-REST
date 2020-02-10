package com.springboottest.app.service;

import com.springboottest.app.model.User;
import com.springboottest.app.repo.DaoImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServDao implements Serv {
    private DaoImpl dao;

    @Autowired
    public ServDao(DaoImpl dao) {
        this.dao = dao;
    }

    public List<User> getAllUsers() {
        return this.dao.getAllUsers();
    }

    @Override
    public User getUser(int id) {
        return dao.getUser(id);
    }

    @Override
    public int addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public int update(User user, int id) {
        return dao.update(user, id);
    }
}
