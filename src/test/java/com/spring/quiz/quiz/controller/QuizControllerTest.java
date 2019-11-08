package com.spring.quiz.quiz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.service.QuizService;
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
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class QuizControllerTest {

    @InjectMocks
    QuizController quizController;

    private MockMvc mockMvc;

    @Mock
    QuizService quizService;

    Quiz mockQuiz;
    String mockQuizToString;
    ArrayList<Quiz> mockQuizList;
    Question mockQuestion;
    Set<Question> mockQuestionSet;
    ObjectMapper mapper;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();

        mockQuestion = new Question("12345", "test_statement","test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_answer");
        mockQuestionSet = new HashSet<>();
        mockQuestionSet.add(mockQuestion);

        mockQuiz = new Quiz();
        mockQuiz.setId("12345");
        mockQuiz.setName("test_name");
        mockQuiz.setQuestionSet(mockQuestionSet);

        mockQuizList = new ArrayList();
        mockQuizList.add(mockQuiz);

        mapper = new ObjectMapper();
        mockQuizToString = mapper.writeValueAsString(mockQuiz);
    }

    @Test
    public void retrieveQuizzes() throws Exception {
        Mockito.when(quizService.retrieveQuizzes()).thenReturn(mockQuizList);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/quizzes/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void createQuiz() throws Exception {
        Mockito.when(quizService.createQuiz(mockQuiz)).thenReturn(ResponseEntity.ok(mockQuiz));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/quizzes/createQuiz")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockQuizToString))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void createQuizWithQuestions() throws Exception {
        Mockito.when(quizService.createQuizWithQuestions(mockQuiz)).thenReturn(ResponseEntity.ok(mockQuiz));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/quizzes/createQuizWithQuestions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockQuizToString))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteQuiz() throws Exception {
        Mockito.when(quizService.deleteQuiz(mockQuiz.getId())).thenReturn(ResponseEntity.ok(mockQuiz));
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/quizzes/deleteQuiz/{quizId}", mockQuiz.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateQuiz() throws Exception {
        Mockito.when(quizService.updateQuiz(mockQuiz, mockQuiz.getId())).thenReturn(ResponseEntity.ok(mockQuiz));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/quizzes/updateQuiz/{quizId}", mockQuiz.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockQuizToString))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addQuestion() throws Exception {
        Mockito.when(quizService.addQuestion(mockQuiz.getId(), mockQuestion.getId())).thenReturn(ResponseEntity.ok(mockQuiz));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/quizzes/updateQuiz/quiz/{quizId}/addQuestion/{questionId}", mockQuiz.getId(), mockQuestion.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockQuizToString))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}