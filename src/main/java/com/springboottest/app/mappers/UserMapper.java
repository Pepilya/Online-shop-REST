package com.springboottest.app.mappers;

import com.springboottest.app.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    List <User> getAllUsers();

    User getUserById(int id);

    int addUser(String login, String password);

    void addRoleToUser(int userId, List<Integer> roles);

    int updateUser(User user, int userId);

    Integer deleteUser(int userId);

    void deleteRole(int userId);
}
