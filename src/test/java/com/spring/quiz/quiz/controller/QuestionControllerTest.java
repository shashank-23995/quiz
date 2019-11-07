package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @InjectMocks
    QuestionController questionController;

    @Mock
    private QuestionService questionService;
    private MockMvc mockMvc;

    Question mockQuestion;
    ArrayList<Question> mockQuestionList;
    String expectedList;
    String expected;
    @Before
    public void setup() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
        mockQuestion = new Question("12345", "test_statement","test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_answer");
        mockQuestionList = new ArrayList();
        mockQuestionList.add(mockQuestion);
        expectedList = "[{id:12345, statement:test_statement, option1:test_option_1, option2:test_option_2, option3:test_option_3, option4:test_option_4, answer:test_answer}]";
        expected = "{id:12345, statement:test_statement, option1:test_option_1, option2:test_option_2, option3:test_option_3, option4:test_option_4, answer:test_answer}";
    }

    @Test
    public void retrieveQuestions() throws Exception {
        Mockito.when(questionService.retrieveQuestions()).thenReturn(mockQuestionList);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
        .get("/questions/getAll")
        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals(expectedList, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void createQuestion() throws Exception {
        Mockito.when(questionService.createQuestion(mockQuestion)).thenReturn(ResponseEntity.ok(mockQuestion));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
        .post("/questions/createQuestion")
        .accept(MediaType.APPLICATION_JSON).content(String.valueOf(mockQuestion)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void deleteQuestion() {
    }

    @Test
    public void updateQuestion() {
    }

    @Test
    public void getQuestionByStatement() {
    }
}