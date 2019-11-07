package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class QuizServiceTest {

    @MockBean
    QuizService quizService;

    Quiz mockQuiz;
    ArrayList<Quiz> mockQuizList;
    Question mockQuestion;
    Set<Question> mockQuestionSet;

    @Before
    public void setup() {
        mockQuestion = new Question("12345", "test_statement","test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_answer");
        mockQuestionSet = new HashSet<>();
        mockQuestionSet.add(mockQuestion);

        mockQuiz = new Quiz();
        mockQuiz.setId("12345");
        mockQuiz.setName("test_name");
        mockQuiz.setQuestionSet(mockQuestionSet);

        mockQuizList = new ArrayList();
        mockQuizList.add(mockQuiz);
    }

    @Test
    public void retrieveQuizzes() {
        Mockito.when(quizService.retrieveQuizzes()).thenReturn(mockQuizList);
        assertEquals(mockQuizList, quizService.retrieveQuizzes());
    }

    @Test
    public void createQuiz() throws ResourceNotFoundException {
        Mockito.when(quizService.createQuiz(mockQuiz)).thenReturn(ResponseEntity.ok(mockQuiz));
        assertEquals(ResponseEntity.ok(mockQuiz), quizService.createQuiz(mockQuiz));
    }

    @Test
    public void deleteQuiz() {
        quizService.deleteQuiz(mockQuiz.getId());
        Mockito.verify(quizService, Mockito.times(1)).deleteQuiz(mockQuiz.getId());
    }

    @Test
    public void updateQuiz() {
        Mockito.when(quizService.updateQuiz(mockQuiz, mockQuiz.getId())).thenReturn(ResponseEntity.ok(mockQuiz));
        assertEquals(ResponseEntity.ok(mockQuiz), quizService.updateQuiz(mockQuiz, mockQuiz.getId()));
    }

    @Test
    public void addQuestion() throws ResourceNotFoundException {
        Mockito.when(quizService.addQuestion(mockQuiz.getId(), mockQuestion.getId())).thenReturn(ResponseEntity.ok(mockQuiz));
        assertEquals(ResponseEntity.ok(mockQuiz), quizService.addQuestion(mockQuiz.getId(), mockQuestion.getId()));
    }

    @Test
    public void findQuizQuestion() throws ResourceNotFoundException {
        Mockito.when(quizService.findQuizQuestion(mockQuiz.getId(), mockQuestion.getId())).thenReturn(true);
        assertEquals(true, quizService.findQuizQuestion(mockQuiz.getId(), mockQuestion.getId()));
    }

    @Test
    public void createQuizWithQuestions() throws Exception {
        Mockito.when(quizService.createQuizWithQuestions(mockQuiz)).thenReturn(ResponseEntity.ok(mockQuiz));
        assertEquals(ResponseEntity.ok(mockQuiz), quizService.createQuizWithQuestions(mockQuiz));
    }
}