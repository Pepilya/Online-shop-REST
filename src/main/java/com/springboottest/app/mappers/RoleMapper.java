package com.springboottest.app.mappers;

import com.springboottest.app.model.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RoleMapper {

    Role getRoleById(int roleId);

    Role addRole(String name);

    Role deleteRole(int roleId);
}
