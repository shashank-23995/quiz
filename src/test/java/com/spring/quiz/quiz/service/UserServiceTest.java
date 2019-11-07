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
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

//import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UserServiceTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    User mockUser;
    String mockUserToString;
    ArrayList<User> mockUserList;

    @Before
    public void setup() throws JsonProcessingException {
        mockUser = new User("12345","test", "test", "test@gmail.com", "test");
        mockUserList = new ArrayList();
        mockUserList.add(mockUser);
//        mockUserToString = mapper.writeValueAsString(mockUser);
    }

    @Test
    public void retrieveUsers() {
        mockUser = new User("12345","test", "test", "test@gmail.com", "test");
        mockUserList = new ArrayList();
        mockUserList.add(mockUser);

        Mockito.when(userService.retrieveUsers()).thenReturn(mockUserList);
        assertEquals(mockUserList, userService.retrieveUsers());
    }

    @Test
    public void createUser() throws ResourceNotFoundException {
        Mockito.when(userService.createUser(mockUser)).thenReturn(ResponseEntity.ok(mockUser));
        assertEquals(ResponseEntity.ok(mockUser), userService.createUser(mockUser));
    }

    @Test
    public void deleteUser() throws ResourceNotFoundException {
        userService.deleteUser(mockUser.getId());
        Mockito.verify(userService, Mockito.times(1)).deleteUser(mockUser.getId());
    }

    @Test
    public void updateUser() throws ResourceNotFoundException {
        Mockito.when(userService.updateUser(mockUser, mockUser.getId())).thenReturn(ResponseEntity.ok(mockUser));
        assertEquals(ResponseEntity.ok(mockUser), userService.updateUser(mockUser, mockUser.getId()));
    }

    @Test
    public void welcomeMessage() {
        String mockWelcomeMessage = "Welcome User";
        Mockito.when(userService.welcomeMessage()).thenReturn(mockWelcomeMessage);
        assertEquals(mockWelcomeMessage, userService.welcomeMessage());
    }
}