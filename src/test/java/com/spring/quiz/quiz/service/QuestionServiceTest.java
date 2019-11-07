package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class QuestionServiceTest {

    @MockBean
    private QuestionService questionService;

    Question mockQuestion;
    ArrayList<Question> mockQuestionList;
    @Before
    public void setup() {
        mockQuestion = new Question("12345", "test statement","test option 1", "test option 2", "test option 3", "test option 4", "test answer");
        mockQuestionList = new ArrayList();
        mockQuestionList.add(mockQuestion);
    }
    @Test
    public void retrieveQuestions() {
        Mockito.when(questionService.retrieveQuestions()).thenReturn(mockQuestionList);
        assertEquals(mockQuestionList, questionService.retrieveQuestions());
    }

    @Test
    public void createQuestion() throws ResourceNotFoundException {
        Mockito.when(questionService.createQuestion(mockQuestion)).thenReturn(ResponseEntity.ok(mockQuestion));
        assertEquals(ResponseEntity.ok(mockQuestion), questionService.createQuestion(mockQuestion));
    }

    @Test
    public void deleteQuestion() throws ResourceNotFoundException {
        Mockito.when(questionService.deleteQuestion(mockQuestion.getId())).thenReturn(ResponseEntity.ok(mockQuestion));
        assertEquals(ResponseEntity.ok(mockQuestion), questionService.createQuestion(mockQuestion));
    }

    @Test
    public void updateQuestion() throws ResourceNotFoundException {
        Mockito.when(questionService.updateQuestion(mockQuestion, mockQuestion.getId())).thenReturn(ResponseEntity.ok(mockQuestion));
        assertEquals(ResponseEntity.ok(mockQuestion), questionService.updateQuestion(mockQuestion, mockQuestion.getId()));
    }

    @Test
    public void getQuestionByStatement() {
        Mockito.when(questionService.getQuestionByStatement(mockQuestion.getStatement())).thenReturn(ResponseEntity.ok(mockQuestion));
        assertEquals(ResponseEntity.ok(mockQuestion), questionService.getQuestionByStatement(mockQuestion.getStatement()));
    }

    @Test
    public void validateAnswer() throws ResourceNotFoundException {
        Mockito.when(questionService.validateAnswer(mockQuestion.getId(), mockQuestion.getAnswer())).thenReturn(true);
        assertEquals(true, questionService.validateAnswer(mockQuestion.getId(), mockQuestion.getAnswer()));
    }
}