package com.springboottest.app.controllers;

import com.springboottest.app.config.JWTUtil;
import com.springboottest.app.model.AuthRequest;
import com.springboottest.app.model.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtTokenUtil;

    @PostMapping("/authLogin")
    @ResponseStatus(HttpStatus.OK)
    AuthResponse authLogin(@RequestBody AuthRequest authRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
            System.out.println(authentication);
        } catch (BadCredentialsException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login or password are incorrect", exception);
        }
        final String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        System.out.println(jwt);

        return new AuthResponse(jwt);
    }
}
