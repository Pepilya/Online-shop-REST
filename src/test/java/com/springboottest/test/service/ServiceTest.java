package com.springboottest.test.service;

import com.springboottest.app.model.User;

import com.springboottest.app.repo.Dao;
import com.springboottest.app.service.ServDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class ServiceTest {

    @Mock
    private Dao dao;

    @InjectMocks
    private ServDao service;

    @Test
    public void getUser() throws Exception {
        User exp = new User(1, "Maksim", "maksim@mail.com");
        when(dao.getUser(3)).thenReturn(exp);
        assertThat(service.getUser(3), is(notNullValue()));
    }

    @Test
    public void getAllUser() throws Exception{
        User user1 = new User(1, "Maksim", "maksim@mail.com");
        User user2 = new User(3, "Sasha", "sasha@mail.com");
        List <User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        when(dao.getAllUsers()).thenReturn(list);
        assertThat(service.getAllUsers(), is(list));
        assertThat(service.getAllUsers().size(), is(2));
    }

    @Test
    public void addUser() throws Exception{
        User user = new User(1, "Maksim", "maksim@mail.com");
        when(dao.addUser(user)).thenReturn(user);
        assertThat(service.addUser(user), is(user));
    }

    @Test
    public void updateUser() throws Exception{
        User user1 = new User(1, "Maksim", "maksim@mail.com");
        User user2 = new User(2, "Dima", "dima@mail.com");
        when(dao.update(user2, user1.getId())).thenReturn(user2);
        assertThat(service.update(user2, user1.getId()), is(user2));
    }
    @Test
    public void deleteUser() throws Exception{
        when(dao.delete(1)).thenReturn(1);
        assertThat(service.delete(1), is(1));
    }
}