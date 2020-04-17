package com.springboottest.app.service;

import com.springboottest.app.exceptions.EntityDoesNotExist;
import com.springboottest.app.mappers.UserMapper;
import com.springboottest.app.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleService roleService) {
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        User user = userMapper.getUserById(id);
        if (user == null)
            throw new EntityDoesNotExist();
        return user;
    }

    @Override
    public User addUser(User user,  List <Integer> roles) {
        if (roles != null && !roles.stream().allMatch(s -> roleService.getRoleById(s) != null))
            throw new EntityDoesNotExist();
        int userId = userMapper.addUser(user.getLogin(), user.getPassword());
        userMapper.addRoleToUser(userId, roles);
        return getUserById(userId);
    }

    @Override
    public User update(User user, int id, List<Integer> roles) {

        if (!isEntityExits(id, roles))
            throw new EntityDoesNotExist();

        int userId = userMapper.updateUser(user, id);
        if (roles != null) {
            userMapper.deleteRole(userId);
            userMapper.addRoleToUser(userId, roles);
        }
        return getUserById(userId);
    }

    private boolean isEntityExits(int id, List<Integer> roles) {
        boolean isRoleExits = true;
        if ((roles != null && !roles.stream().allMatch(s -> roleService.getRoleById(s) != null))
                || getUserById(id) == null)
            isRoleExits = false;
        return isRoleExits;
    }

    @Override
    public Integer delete(int id) {
        return userMapper.deleteUser(id);
    }
}
