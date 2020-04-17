package com.springboottest.app.service;

import com.springboottest.app.exceptions.EntityDoesNotExist;
import com.springboottest.app.mappers.RoleMapper;
import com.springboottest.app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Role getRoleById(int roleId) {
        Role role = roleMapper.getRoleById(roleId);
        if (role == null)
            throw new EntityDoesNotExist();
        return role;
    }

    @Override
    public Role addRole(Role role) {
        return roleMapper.addRole(role.getName());
    }

    @Override
    public Role deleteRole(int roleId) {
        Role role = roleMapper.deleteRole(roleId);
        if (role == null)
            throw new EntityDoesNotExist();
        return role;
    }
}
