//package com.springboottest.test.service;
//
//import com.springboottest.app.model.User;
//
//import com.springboottest.app.service.UserServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import static org.mockito.Mockito.*;
//
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//
//@ExtendWith(MockitoExtension.class)
//public class ServiceTest {
//
//    @Mock
//    private Dao dao;
//
//    @InjectMocks
//    private UserServiceImpl service;
//
//
//
//    @Test
//    public void getUser_whenUserExist_thenReturnAndOk() throws Exception {
//        User exp = new User(1, "Maksim", "maksim@mail.com");
//        when(dao.getUser(exp.getId())).thenReturn(exp);
//        assertThat(service.getUser(exp.getId()), is(notNullValue()));
//        assertThat(service.getUser(exp.getId()), is(exp));
//    }
//
//    @Test
//    public void getAllUsers_whenUsersExists_thenReturnAndOk() throws Exception {
//        User user1 = new User(1, "Maksim", "maksim@mail.com");
//        User user2 = new User(3, "Sasha", "sasha@mail.com");
//        List<User> list = Arrays.asList(user1, user2);
//        when(dao.getAllUsers()).thenReturn(list);
//        assertThat(service.getAllUsers(), is(list));
//        assertThat(service.getAllUsers().size(), is(2));
//    }
//
//    @Test
//    public void addUser_whenUserNotExist_thenReturnUserAndOk() throws Exception {
//        User user = new User(1, "Maksim", "maksim@mail.com");
//        when(dao.addUser(user)).thenReturn(user);
//        assertThat(service.addUser(user), is(user));
//    }
//
//    @Test
//    public void updateUser_whenUserExist_thenReturnUserAndOk() throws Exception {
//        User user1 = new User(1, "Maksim", "maksim@mail.com");
//        User user2 = new User(2, "Dima", "dima@mail.com");
//        when(dao.update(user2, user1.getId())).thenReturn(user2);
//        assertThat(service.update(user2, user1.getId()), is(user2));
//    }
//
//    @Test
//    public void deleteUser_whenUserExist_thenReturnUserAndOk() throws Exception {
//        User user = new User(1, "Ilia", "ilia@mail.com");
//        when(dao.delete(user.getId())).thenReturn(user);
//        assertThat(service.delete(user.getId()), is(user));
//    }
//}