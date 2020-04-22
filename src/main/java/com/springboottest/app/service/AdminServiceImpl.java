package com.springboottest.app.service;

import com.springboottest.app.exceptions.EntityDoesNotExist;
import com.springboottest.app.mappers.AdminMapper;
import com.springboottest.app.model.Role;
import com.springboottest.app.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return adminMapper.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        User user = adminMapper.getUserById(id);
        if (user == null)
            throw new EntityDoesNotExist();
        return user;
    }

    @Override
    public User addUser(User user,  List <Integer> roles) {
        if (roles != null && !roles.stream().allMatch(s -> getRoleById(s) != null))
            throw new EntityDoesNotExist();
        int userId = adminMapper.addUser(user.getLogin(), user.getPassword());
        adminMapper.addRoleToUser(userId, roles);
        if (adminMapper.guestRoleExits(userId))
            adminMapper.createUserDeposit(userId);
        return getUserById(userId);
    }

    @Override
    public User update(User user, int id, List<Integer> roles) {

        if (!isEntityExits(id, roles))
            throw new EntityDoesNotExist();

        int userId = adminMapper.updateUser(user, id);
        if (roles != null) {
            adminMapper.deleteRolesFromUser(userId);
            adminMapper.addRoleToUser(userId, roles);
        }
        return getUserById(userId);
    }

    private boolean isEntityExits(int id, List<Integer> roles) {
        boolean isRoleExits = true;
        if ((roles != null && !roles.stream().allMatch(s -> getRoleById(s) != null))
                || getUserById(id) == null)
            isRoleExits = false;
        return isRoleExits;
    }

    @Override
    public Integer delete(int id) {
        return adminMapper.deleteUser(id);
    }

    @Override
    public Role getRoleById(int roleId) {
        Role role = adminMapper.getRoleById(roleId);
        if (role == null)
            throw new EntityDoesNotExist();
        return role;
    }

    @Override
    public Role addRole(Role role) {
        return adminMapper.addRole(role.getName());
    }

    @Override
    public Role deleteRole(int roleId) {
        Role role = adminMapper.deleteRole(roleId);
        if (role == null)
            throw new EntityDoesNotExist();
        return role;
    }
}
