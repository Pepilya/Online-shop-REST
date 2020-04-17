package com.springboottest.app.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Role {
    private int id;
    @NotNull
    private String name;
}
