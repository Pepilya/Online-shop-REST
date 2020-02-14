package com.springboottest.test.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboottest.app.controller.UserController;
import com.springboottest.app.model.User;


import com.springboottest.app.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
public class ControllerTest {


    private MockMvc mvc;
    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void getAllUsers_whenUsersExists_thenReturnAndOk() throws Exception {
        User user1 = new User(3, "Maksim", "maksim@mail.com");
        User user2 = new User(1, "Sasha", "sasha@mail.com");
        List<User> list = Arrays.asList(user1, user2);
        Mockito.when(service.getAllUsers()).thenReturn(list);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk()).andReturn();
        List<User> resultList = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {
        });
        assertThat(resultList.size(), is(2));
        assertThat(resultList, is(list));
    }

    @Test
    public void getUser_whenUserExist_thenReturnAndOk() throws Exception {
        User user1 = new User(1, "Maksim", "maksim@mail.com");
        when(service.getUser(user1.getId())).thenReturn(user1);
        mvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1.getId()))
                .andExpect(jsonPath("$.name").value(user1.getName()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()));
        verify(service).getUser(1);
    }

    @Test
    public void addUser_whenUserNotExist_thenReturnUserAndOk() throws Exception {
        User user = new User(1, "Ilia", "ilia@mail.com");
        when(service.addUser(user)).thenReturn(user);
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
        verify(service).addUser(user);
    }

    @Test
    public void updateUser_whenUserExist_thenReturnUserAndOk() throws Exception {
        User user = new User(1, "Ilia", "ilia@mail.com");
        when(service.update(user, user.getId())).thenReturn(user);
        mvc.perform(MockMvcRequestBuilders.put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
        verify(service).update(user, user.getId());
    }

    @Test
    public void deleteUser_whenUserExist_thenReturnUserAndOk() throws Exception {
        User user = new User(1, "Ilia", "ilia@mail.com");
        when(service.delete(user.getId())).thenReturn(user);
        mvc.perform(MockMvcRequestBuilders.delete("/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
        verify(service).delete(user.getId());
    }

}

