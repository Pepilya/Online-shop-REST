package com.springboottest.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboottest.app.controller.UserController;
import com.springboottest.app.model.User;


import com.springboottest.app.service.Serv;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
public class ControllerTest {

    private MockMvc mvc;
    @Mock
    private Serv service;

    @InjectMocks
    private UserController controller;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void getAllUsers() throws Exception {
        User user1 = new User(3, "Maksim", "maksim@mail.com");
        User user2 = new User(1, "Sasha", "sasha@mail.com");
        List <User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        Mockito.when(service.getAllUsers()).thenReturn(list);
        mvc.perform(MockMvcRequestBuilders.get("/app"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUser() throws Exception {
        User user1 = new User(1, "Maksim", "maksim@mail.com");
        when(service.getUser(user1.getId())).thenReturn(user1);
        mvc.perform(MockMvcRequestBuilders.get("/app/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1.getId()))
                .andExpect(jsonPath("$.name").value(user1.getName()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()));
        verify(service).getUser(1);
    }
    @Test
    public void addUser() throws Exception {
        User user = new User(1,"Ilia","ilia@mail.com");
        when(service.addUser(user)).thenReturn(user);
        MvcResult result = mvc.perform(post("/app")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(user)))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
        User resultUser = new ObjectMapper().readValue(result.getResponse().getContentAsString(), User.class);
        assertEquals(user.getId(), resultUser.getId());
        assertEquals(user.getName(), resultUser.getName());
        assertEquals(user.getEmail(), resultUser.getEmail());

    }

    @Test
    public void updateItem() throws Exception {
        User user = new User(1,"Ilia","ilia@mail.com");
        when(service.update(user, user.getId())).thenReturn(user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/app/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(user)))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
        User resultUser = new ObjectMapper().readValue(result.getResponse().getContentAsString(), User.class);
        assertEquals(user.getId(), resultUser.getId());
        assertEquals(user.getName(), resultUser.getName());
        assertEquals(user.getEmail(), resultUser.getEmail());
    }

    @Test
    public void deleteItem() throws Exception{
        when(service.delete(1)).thenReturn(1);
        mvc.perform(MockMvcRequestBuilders.delete("/app/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}

