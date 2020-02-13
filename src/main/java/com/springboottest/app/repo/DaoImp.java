package com.springboottest.app.repo;

import com.springboottest.app.model.User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImp implements Dao {
    final private JdbcTemplate jdbcTemplate;

    private final static Logger LOGGER = LoggerFactory.getLogger(DaoImp.class);

    @Autowired
    public DaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Request to DB: Get all users");

        UserMapper userMapper = new UserMapper();
        String sql = "Select * from user_table";
        return jdbcTemplate.query(sql, userMapper);
    }

    @Override
    public User getUser(int id) {
        LOGGER.info("Request to DB: Get " + id + " user");

        UserMapper userMapper = new UserMapper();
        String sql = "Select * from user_table where user_id=?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }

    @Override
    public User addUser(User user) {
        LOGGER.info("Request to DB: Post " + user.toString());

        UserMapper userMapper = new UserMapper();
        String sql = "Insert into user_table (user_name, user_email) values (?, ?) Returning user_id, user_name, user_email";
        Object[] args = new Object[]{user.getName(), user.getEmail()};
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }

    @Override
    public User update(User user, int id) {
        LOGGER.info("Request to DB: Update " + id + " user to " + user.toString());

        UserMapper userMapper = new UserMapper();
        String sql = "Update user_table set user_name=?, user_email=? where user_id=? Returning user_id, user_name, user_email";
        Object[] args = new Object[]{user.getName(), user.getEmail(), id};
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }

    @Override
    public User delete(int id) {
        LOGGER.info("Request to DB: Delete " + id + " user");

        UserMapper userMapper = new UserMapper();
        String sql = "Delete from user_table where user_id=? Returning user_id, user_name, user_email";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }
}
