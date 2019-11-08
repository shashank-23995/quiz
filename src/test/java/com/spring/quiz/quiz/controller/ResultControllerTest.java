package com.spring.quiz.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.model.Result;
import com.spring.quiz.quiz.model.User;
import com.spring.quiz.quiz.service.ResultService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ResultControllerTest {

    @InjectMocks
    ResultController resultController;

    private MockMvc mockMvc;

    @Mock
    ResultService resultService;

    Result mockResult;
    String mockResultToString;
    ArrayList<Result> mockResultList;
    HashMap<String, String> mockSelectedAnswer;
    User mockUser;
    Quiz mockQuiz;
    Question mockQuestion;
    Set<Question> mockQuestionSet;
    String mockSelectedOption;
    ObjectMapper mapper;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(resultController).build();

        mockUser = new User("12345","test", "test", "test@gmail.com", "test", "CANDIDATE");
        mockSelectedAnswer = new HashMap<>();
        mockSelectedAnswer.put("12345", "test_answer_1");
        mockSelectedAnswer.put("67890", "test_answer_2");
        mockResult = new Result("12345", "12345", "12345", mockSelectedAnswer, 3, 5);
        mockResultList = new ArrayList();
        mockResultList.add(mockResult);

        mockQuestion = new Question("12345", "test_statement","test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_answer");
        mockQuestionSet = new HashSet<>();
        mockQuestionSet.add(mockQuestion);

        mockQuiz = new Quiz();
        mockQuiz.setId("12345");
        mockQuiz.setName("test_name");
        mockQuiz.setQuestionSet(mockQuestionSet);
        mockSelectedOption = "mockAnswer";

        mapper = new ObjectMapper();
        mockResultToString = mapper.writeValueAsString(mockResult);
    }

    @Test
    public void selectAnswer() throws Exception {
        String mockSelectedOption = "test_selected_option";
        Mockito.when(resultService.updateResult(mockQuiz.getId(), mockQuestion.getId(), "test_selected_option", true)).thenReturn(ResponseEntity.ok(mockResult));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/results/submitAnswer/quiz/{quizId}/question/{questionId}/answer/{selectedOption}", mockQuiz.getId(), mockQuestion.getId(), mockSelectedOption)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getResultByUserQuiz() throws Exception {
        Mockito.when(resultService.getResultByUserQuiz(mockUser.getId(), mockQuiz.getId())).thenReturn(ResponseEntity.ok(mockResult));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/results/user/{userId}/quiz/{quizId}", mockUser.getId(), mockQuiz.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void submitQuiz() throws Exception {
        Mockito.when(resultService.submitQuiz(mockUser.getId(), mockQuiz.getId(), mockResult)).thenReturn(ResponseEntity.ok(mockResult));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/results/submitQuiz/user/{userId}/quiz/{quizId}", mockUser.getId(), mockQuiz.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockResultToString))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}