package com.spring.quiz.quiz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.quiz.quiz.QuizApplication;
import com.spring.quiz.quiz.QuizApplicationTests;
import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.User;
import com.spring.quiz.quiz.repository.UserRepository;
import com.spring.quiz.quiz.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    private UserService userService;
     private MockMvc mockMvc;

                                      
    @Mock
    private UserRepository userRepository;

//    @Autowired
//    ObjectMapper mapper;

    User mockUser;
    String mockUserToString;
    ArrayList<User> mockUserList;
    String requestURI = "/users/getAll";

    @Before
    public void setup() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockUser = new User("12345","test", "test", "test@gmail.com", "test");
        mockUserList = new ArrayList();
        mockUserList.add(mockUser);
//        mockUserToString = mapper.writeValueAsString(mockUser);
    }


    @Test
    public void retrieveUsers() throws Exception {
//        mockUser = new User("12345","test", "test", "test@gmail.com", "test");
//        mockUserList = new ArrayList();
//        mockUserList.add(mockUser);

//        Mockito.when(userService.retrieveUsers()).thenReturn(mockUserList);
//        assertEquals(mockUserList, userService.retrieveUsers());




//         given
//        Mockito.when(userService.retrieveUsers()).thenReturn(mockUserList);
//
        String expected = "[{id:12345, firstName:test, lastName:test, email:test@gmail.com, password: test}]";

        // when
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/getAll"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().json(expected))
//                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
//        Mockito.verify(userService).retrieveUsers();

        Mockito.when(userService.retrieveUsers()).thenReturn(mockUserList);
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                .get("/users/getAll")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
//                .andExpect(MockMvcResultMatchers.content().json(expected));
//                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
         JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void welcomeMessage() throws Exception {
        String mockWelcomeMessage = "Welcome User";
        Mockito.when(userService.welcomeMessage()).thenReturn(mockWelcomeMessage);
        assertEquals(mockWelcomeMessage, userService.welcomeMessage());
//        assertEquals("mockWelcomeMessage", "mockWelcomeMessage");


        mockMvc.perform( MockMvcRequestBuilders
                .get("/users/welcome")
                .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.content().json(expected));
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
}