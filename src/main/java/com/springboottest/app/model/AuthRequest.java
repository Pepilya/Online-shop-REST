package com.springboottest.app.model;

import lombok.Data;

@Data
public class AuthRequest {
    private final String login;
    private final String password;
}
