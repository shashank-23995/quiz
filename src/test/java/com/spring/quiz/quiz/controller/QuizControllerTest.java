package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.service.QuizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class QuizControllerTest {

    @InjectMocks
    QuizController quizController;

    MockMvc mockMvc;

    @Mock
    QuizService quizService;

    Quiz mockQuiz;
    ArrayList<Quiz> mockQuizList;
    Question mockQuestion;
    Set<Question> mockQuestionSet;

    public void setup() {
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
    }

    @Test
    public void retrieveQuizzes() {
    }

    @Test
    public void createQuiz() {
    }

    @Test
    public void createQuizWithQuestions() {
    }

    @Test
    public void deleteQuiz() {
    }

    @Test
    public void updateQuiz() {
    }

    @Test
    public void addQuestion() {
    }
}