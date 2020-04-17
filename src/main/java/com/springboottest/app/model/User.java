package com.springboottest.app.model;

import com.springboottest.app.validators.RoleValid;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class User {
    private int id;
    @NotNull
    private String login;
    @NotNull
    private String password;

    private List<Role> listRoles;
}
