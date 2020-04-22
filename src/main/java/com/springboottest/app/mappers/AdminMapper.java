package com.springboottest.app.mappers;

import com.springboottest.app.model.Role;
import com.springboottest.app.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AdminMapper {

    List <User> getAllUsers();

    User getUserById(int id);

    int addUser(String login, String password);

    void addRoleToUser(int userId, List<Integer> roles);

    int updateUser(User user, int userId);

    Integer deleteUser(int userId);

    void deleteRolesFromUser(int userId);

    Role getRoleById(int roleId);

    Role addRole(String name);

    Role deleteRole(int roleId);

    void createUserDeposit(int userId);

    boolean guestRoleExits(int userId);
}
