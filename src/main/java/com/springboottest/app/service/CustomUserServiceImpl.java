package com.springboottest.app.service;

import com.springboottest.app.exceptions.EntityDoesNotExist;
import com.springboottest.app.mappers.AdminMapper;
import com.springboottest.app.model.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<String> roles = new ArrayList<>();
        String [] arrayRoles = null;
        CustomUser customUser = adminMapper.getUserByLogin(userName);
        if (customUser == null)
            throw new EntityDoesNotExist();
        if (customUser.getListRoles() != null){
            arrayRoles = new String[customUser.getListRoles().size()];
            customUser.getListRoles().forEach(s -> roles.add(s.getName()));
            arrayRoles = roles.toArray(arrayRoles);
        }
        UserDetails userDetails = User.builder()
                .username(customUser.getLogin())
                .password(customUser.getPassword())
                .roles(arrayRoles).build();

        return userDetails;
    }
}
