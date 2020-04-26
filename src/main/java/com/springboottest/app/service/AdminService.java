package com.springboottest.app.service;

import com.springboottest.app.model.Role;
import com.springboottest.app.model.CustomUser;

import java.util.List;

public interface AdminService {

    List<CustomUser> getAllUsers();

    CustomUser getUserById(int id);

    CustomUser addUser(CustomUser user, List<Integer> roles);

    CustomUser update(CustomUser user, int id, List<Integer> roles);

    Integer delete(int id);

    Role getRoleById(int roleId);

    Role addRole(Role role);

    Role deleteRole(int roleId);
}
