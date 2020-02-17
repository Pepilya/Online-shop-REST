package com.springboottest.app.repo;

import com.springboottest.app.model.User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements Dao {
    private final JdbcTemplate jdbcTemplate;

    private final static Logger LOGGER = LoggerFactory.getLogger(DaoImpl.class);

    @Autowired
    public DaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Request to DB: Get all users");

        UserMapper userMapper = new UserMapper();
        String sql = "SELECT * FROM user_table";
        return jdbcTemplate.query(sql, userMapper);
    }

    @Override
    public User getUser(int id) {
        LOGGER.info("Request to DB: Get " + id + " user");

        UserMapper userMapper = new UserMapper();
        String sql = "SELECT * FROM user_table WHERE user_id=?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }

    @Override
    public User addUser(User user) {
        LOGGER.info("Request to DB: Post " + user.toString());

        UserMapper userMapper = new UserMapper();
        String sql = "INSERT INTO user_table (user_name, user_email) VALUES (?, ?) RETURNING user_id, user_name, user_email";
        Object[] args = new Object[]{user.getName(), user.getEmail()};
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }

    @Override
    public User update(User user, int id) {
        LOGGER.info("Request to DB: Update " + id + " user to " + user.toString());

        String sql;
        Object[] args;

        if (user.getName() != null && user.getEmail() != null) {
            sql = "UPDATE user_table SET user_name=?, user_email=? WHERE user_id=? RETURNING user_id, user_name, user_email";
            args = new Object[]{user.getName(), user.getEmail(), id};
        } else if (user.getName() != null) {
            sql = "UPDATE user_table SET user_name=? WHERE user_id=? RETURNING user_id, user_name, user_email";
            args = new Object[]{user.getName(), id};
        } else if (user.getEmail() != null) {
            sql = "UPDATE user_table SET user_email=? WHERE user_id=? RETURNING user_id, user_name, user_email";
            args = new Object[]{user.getEmail(), id};
        } else {
            sql = "SELECT * FROM user_table WHERE user_id=?";
            args = new Object[]{id};
        }
        UserMapper userMapper = new UserMapper();
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }

    @Override
    public User delete(int id) {
        LOGGER.info("Request to DB: Delete " + id + " user");

        UserMapper userMapper = new UserMapper();
        String sql = "DELETE FROM user_table WHERE user_id=? RETURNING user_id, user_name, user_email";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }
}
