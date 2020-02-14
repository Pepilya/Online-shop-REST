package com.springboottest.app.service;

import com.springboottest.app.model.User;
import com.springboottest.app.repo.Dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImp implements UserService {
    private final Dao dao;

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceImp.class);

    @Autowired
    public ServiceImp(Dao dao) {
        this.dao = dao;
    }

    public List<User> getAllUsers() {
        LOGGER.info("Request to user service: Get all users");

        return dao.getAllUsers();
    }

    @Override
    public User getUser(int id) {
        LOGGER.info("Request to user service: Get " + id + " user");

        return dao.getUser(id);
    }

    @Override
    public User addUser(User user) {
        LOGGER.info("Request to user service: Post " + user.toString());

        return dao.addUser(user);
    }

    @Override
    public User delete(int id) {
        LOGGER.info("Request to user service: Delete " + id + " user");

        return dao.delete(id);
    }

    @Override
    public User update(User user, int id) {
        LOGGER.info("Request to user service: Update " + id + " user to " + user.toString());

        return dao.update(user, id);
    }
}
