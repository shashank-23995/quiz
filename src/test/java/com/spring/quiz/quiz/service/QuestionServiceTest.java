package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    Question question;

    Question mockQuestion;
    ArrayList<Question> mockQuestionList;
    @Before
    public void setup() {
        mockQuestion = new Question("12345", "test statement","test option 1", "test option 2", "test option 3", "test option 4", "test option 1");
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
        Mockito.when(questionRepository.insert(mockQuestion)).thenReturn(mockQuestion);
        assertEquals(mockQuestion, questionService.createQuestion(mockQuestion));
    }

    @Test
    public void deleteQuestion() throws ResourceNotFoundException {
        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(mockQuestion, questionService.deleteQuestion(mockQuestion.getId()));
    }

    @Test
    public void updateQuestion() throws ResourceNotFoundException {
        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        Mockito.when(questionRepository.save(mockQuestion)).thenReturn(mockQuestion);
//        Mockito.when(questionRepository.save(mockQuestion).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(mockQuestion, questionService.updateQuestion(mockQuestion, mockQuestion.getId()));
    }

    @Test
    public void getQuestionByStatement() {
        Mockito.when(questionService.getQuestionByStatement(mockQuestion.getStatement())).thenReturn(mockQuestion);
        assertEquals(mockQuestion, questionService.getQuestionByStatement(mockQuestion.getStatement()));
    }

    @Test
    public void validateAnswer() throws ResourceNotFoundException {
//        Mockito.when(questionService.validateAnswer(mockQuestion.getId(), mockQuestion.getAnswer())).thenReturn(true);
        Mockito.when( questionRepository.findById(mockQuestion.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(true, questionService.validateAnswer(mockQuestion.getId(), mockQuestion.getAnswer()));
    }

    @Test
    public void deleteQuestionNotFound() throws ResourceNotFoundException {
//        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(mockQuestion, questionService.deleteQuestion(mockQuestion.getId()));
    }

    @Test
    public void validateAnswerIncorrect() throws ResourceNotFoundException {
//        Mockito.when(questionService.validateAnswer(mockQuestion.getId(), mockQuestion.getAnswer())).thenReturn(true);
        Mockito.when( questionRepository.findById(mockQuestion.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(true, questionService.validateAnswer(mockQuestion.getId(), "mockQuestion.getAnswer()"));
    }

    @Test
    public void validateAnswerNotFound() throws ResourceNotFoundException {
//        Mockito.when(questionService.validateAnswer(mockQuestion.getId(), mockQuestion.getAnswer())).thenReturn(true);
//        Mockito.when( questionRepository.findById(mockQuestion.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(true, questionService.validateAnswer(mockQuestion.getId(), mockQuestion.getAnswer()));
    }

    @Test
    public void createQuestionInvalidAnswer() throws ResourceNotFoundException {
        Question mockQuestion1 = new Question("12345", "test statement","test option 1", "test option 2", "test option 3", "test option 4", "test answer");
        Mockito.when(questionRepository.insert(mockQuestion1)).thenReturn(mockQuestion1);
        assertEquals(mockQuestion1, questionService.createQuestion(mockQuestion1));
    }

    @Test
    public void createQuestionEmptyOptions() throws ResourceNotFoundException {
        Question mockQuestion1 = new Question("12345", "test statement","", "test option 2", "test option 3", "test option 4", "test answer");
        Mockito.when(questionRepository.insert(mockQuestion1)).thenReturn(mockQuestion1);
        assertEquals(mockQuestion1, questionService.createQuestion(mockQuestion1));
    }

    @Test
    public void updateQuestionEmptyOptions() throws ResourceNotFoundException {
        Question mockQuestion1 = new Question("12345", "test statement","", "test option 2", "test option 3", "test option 4", "test option 3");
        Mockito.when(questionRepository.findById(mockQuestion1.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion1));
        Mockito.when(questionRepository.save(mockQuestion1)).thenReturn(mockQuestion1);
//        Mockito.when(questionRepository.save(mockQuestion).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(mockQuestion1, questionService.updateQuestion(mockQuestion1, mockQuestion1.getId()));
    }

    @Test
    public void updateQuestionInvalidAnswer() throws ResourceNotFoundException {
        Question mockQuestion1 = new Question("12345", "test statement","test option 1", "test option 2", "test option 3", "test option 4", "test answer");
        Mockito.when(questionRepository.findById(mockQuestion1.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion1));
        Mockito.when(questionRepository.save(mockQuestion1)).thenReturn(mockQuestion1);
//        Mockito.when(questionRepository.save(mockQuestion).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(mockQuestion1, questionService.updateQuestion(mockQuestion1, mockQuestion1.getId()));
    }

    @Test
    public void updateQuestionQuestionNotFound() throws ResourceNotFoundException {
//        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        Mockito.when(questionRepository.save(mockQuestion)).thenReturn(mockQuestion);
//        Mockito.when(questionRepository.save(mockQuestion).thenReturn(java.util.Optional.ofNullable(mockQuestion));
        assertEquals(mockQuestion, questionService.updateQuestion(mockQuestion, mockQuestion.getId()));
    }
}