package com.spring.quiz.quiz.service;

//import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.quiz.quiz.QuizApplicationTests;
import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.User;
import com.spring.quiz.quiz.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

//import static org.junit.Assert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

//import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder encoder;

    @Mock
    User user;

    User mockUser;
    String mockUserToString;
    ArrayList<User> mockUserList;

    @Before
    public void setup() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
        mockUser = new User("12345","test", "test", "test@gmail.com", "test", "CANDIDATE");
        mockUserList = new ArrayList();
        mockUserList.add(mockUser);
//        mockUserToString = mapper.writeValueAsString(mockUser);
    }

    @Test
    public void retrieveUsers() {
        Mockito.when(userService.retrieveUsers()).thenReturn(mockUserList);
        assertEquals(mockUserList, userService.retrieveUsers());
//        Mockito.verify(userService).retrieveUsers();
//        assertThat(userService.retrieveUsers(), userList);
    }

    @Test
    public void createUser() throws ResourceNotFoundException {
//        Mockito.when(userService.createUser(mockUser)).thenReturn(new ResponseEntity<User>(mockUser,HttpStatus.OK));
        Mockito.when(userService.createUser(mockUser)).thenReturn(mockUser);
        assertEquals(mockUser, userService.createUser(mockUser));
    }

    @Test
    public void deleteUser() throws ResourceNotFoundException {
        Mockito.when(userRepository.findById(mockUser.getId())).thenReturn(Optional.ofNullable(mockUser));
        assertEquals(mockUser, userService.deleteUser(mockUser.getId()));
    }

    @Test
    public void updateUser() throws ResourceNotFoundException {
        Mockito.when(userRepository.findById(mockUser.getId())).thenReturn(Optional.ofNullable(mockUser));
        assertEquals(null, userService.updateUser(mockUser, mockUser.getId()));
    }

    @Test
    public void welcomeMessage() {
        String mockWelcomeMessage = "Welcome to quiz application";
//        Mockito.when(userService.welcomeMessage()).thenReturn(mockWelcomeMessage);
        assertEquals(mockWelcomeMessage, userService.welcomeMessage());
    }

    @Test
    public void createUserInvalid() throws ResourceNotFoundException {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setRole("");
//        Mockito.when(userService.createUser(user)).thenReturn(ResponseEntity.ok(user));
        Mockito.when(userService.createUser(user)).thenReturn(user);
        assertEquals(user, userService.createUser(user));
    }

    @Test
    public void deleteUserNotFound() throws ResourceNotFoundException {
//        Mockito.when(userRepository.findById(mockUser.getId())).thenReturn(Optional.ofNullable(mockUser));
        assertEquals(mockUser, userService.deleteUser(mockUser.getId()));
    }

    @Test
    public void updateUserNotFound() throws ResourceNotFoundException {
//        Mockito.when(userRepository.findById(mockUser.getId())).thenReturn(Optional.ofNullable(mockUser));
        assertEquals(null, userService.updateUser(mockUser, mockUser.getId()));
    }
}