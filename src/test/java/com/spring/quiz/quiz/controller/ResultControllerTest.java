package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Result;
import com.spring.quiz.quiz.service.ResultService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ResultControllerTest {

    @InjectMocks
    ResultController resultController;

    private MockMvc mockMvc;

    @Mock
    ResultService resultService;

    Result mockResult;
    ArrayList<Result> mockResultList;
    HashMap<String, String> mockSelectedAnswer;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(resultController).build();

        mockSelectedAnswer = new HashMap<>();
        mockSelectedAnswer.put("12345", "test_answer_1");
        mockSelectedAnswer.put("67890", "test_answer_2");
        mockResult = new Result("12345", "12345", "12345", mockSelectedAnswer, 3, 5);
        mockResultList = new ArrayList();
        mockResultList.add(mockResult);
    }

    @Test
    public void selectAnswer() {
    }

    @Test
    public void getResultByUserQuiz() {
    }

    @Test
    public void submitQuiz() {
    }
}