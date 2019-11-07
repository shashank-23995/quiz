package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.model.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ResultServiceTest {

    @MockBean
    ResultService resultService;

    Result mockResult;
    ArrayList<Result> mockResultList;
    HashMap<String, String> mockSelectedAnswer;

    @Before
    public void setUp() throws Exception {
        mockSelectedAnswer = new HashMap<>();
        mockSelectedAnswer.put("12345", "test_answer_1");
        mockSelectedAnswer.put("67890", "test_answer_2");
        mockResult = new Result("12345", "12345", "12345", mockSelectedAnswer, 3, 5);
        mockResultList = new ArrayList();
        mockResultList.add(mockResult);
    }

    @Test
    public void retrieveAllResult() {
        Mockito.when(resultService.retrieveAllResult()).thenReturn(mockResultList);
        assertEquals(mockResultList, resultService.retrieveAllResult());
    }

    @Test
    public void getResultByUser() {
    }

    @Test
    public void selectAnswer() {
    }

    @Test
    public void updateResult() {
    }

    @Test
    public void getResultByUserQuiz() {
    }

    @Test
    public void submitQuiz() {
    }
}