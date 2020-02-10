package com.springboottest.app.repo;

import com.springboottest.app.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements Dao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        UserMapper userMapper = new UserMapper();
        String sql = "Select * from user_table";
        return jdbcTemplate.query(sql, userMapper);
    }

    @Override
    public User getUser(int id) {
        UserMapper userMapper = new UserMapper();
        String sql = "Select * from user_table where user_id=?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, args, userMapper);
    }

    @Override
    public int addUser(User user) {
        int result;
        String sql = "Insert into user_table (user_id, user_name, user_email) values (?, ?, ?)";
        result = jdbcTemplate.update(sql, new Object[]{user.getId(), user.getName(), user.getEmail()});
        return result;
    }

    @Override
    public int update(User user, int id) {
        int result;
        String sql = "Update user_table set user_id=?, user_name=?, user_email=? where user_id=?";
        Object[] args = new Object[]{user.getId(), user.getName(), user.getEmail(), id};
        result = jdbcTemplate.update(sql, args);
        return result;
    }

    @Override
    public int delete(int id) {
        int result;
        String sql = "Delete from user_table where user_id=?";
        Object[] args = new Object[]{id};
        result = jdbcTemplate.update(sql, args);
        return result;
    }
}
