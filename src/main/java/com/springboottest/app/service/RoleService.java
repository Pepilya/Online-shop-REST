package com.springboottest.app.service;

import com.springboottest.app.model.Role;

public interface RoleService {

    Role getRoleById(int roleId);

    Role addRole(Role role);

    Role deleteRole(int roleId);
}
