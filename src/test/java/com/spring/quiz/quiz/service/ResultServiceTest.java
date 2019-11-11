package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.model.Result;
import com.spring.quiz.quiz.model.User;
import com.spring.quiz.quiz.repository.QuestionRepository;
import com.spring.quiz.quiz.repository.QuizRepository;
import com.spring.quiz.quiz.repository.ResultRepository;
import com.spring.quiz.quiz.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ResultServiceTest {

    @InjectMocks
    ResultService resultService;

    @Mock
    ResultRepository resultRepository;

    @Mock
    QuizRepository quizRepository;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    QuestionService questionService;

    @Mock
    Quiz quiz;

    @Mock
    User user;

    @Mock
    Question question;

    Result mockResult;
    ArrayList<Result> mockResultList;
    HashMap<String, String> mockSelectedAnswer;
    User mockUser;
    Quiz mockQuiz;
    Question mockQuestion;
    Set<Question> mockQuestionSet;
    String mockSelectedOption;

    @Before
    public void setUp() throws Exception {
        mockSelectedAnswer = new HashMap<>();
        mockSelectedAnswer.put("12345", "test_answer_1");
        mockSelectedAnswer.put("67890", "test_answer_2");
        mockResult = new Result("12345", "12345", "12345", mockSelectedAnswer, 3, 5);
        mockResultList = new ArrayList();
        mockResultList.add(mockResult);

        mockQuestion = new Question("12345", "test_statement","test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_answer");
        mockQuestionSet = new HashSet<>();
        mockQuestionSet.add(mockQuestion);

        mockUser = new User("12345","test", "test", "test@gmail.com", "test", "CANDIDATE");

        mockQuiz = new Quiz();
        mockQuiz.setId("12345");
        mockQuiz.setName("test_name");
        mockQuiz.setQuestionSet(mockQuestionSet);
        mockSelectedOption = "mockAnswer";
    }

    @Test
    public void retrieveAllResult() {
        Mockito.when(resultService.retrieveAllResult()).thenReturn(mockResultList);
        assertEquals(mockResultList, resultService.retrieveAllResult());
    }

    @Test
    public void getResultByUser() {
        Mockito.when(resultService.getResultByUser(mockUser.getId(), mockQuiz.getId())).thenReturn(ResponseEntity.ok(mockResult));
        assertEquals((ResponseEntity.ok(mockResult)), resultService.getResultByUser(mockUser.getId(), mockQuiz.getId()));
    }

    @Test
    public void selectAnswer() {
        Mockito.when(resultService.selectAnswer(mockQuiz.getId(), mockQuestion.getId(), mockSelectedOption)).thenReturn(ResponseEntity.ok(mockResult));
        assertEquals(ResponseEntity.ok(mockResult), resultService.selectAnswer(mockQuiz.getId(), mockQuestion.getId(), mockSelectedOption));
    }

    @Test
    public void updateResult() {
//        Mockito.when(resultService.updateResult(mockQuiz.getId(), mockQuestion.getId(), mockSelectedOption, true)).thenReturn(mockResult);
        Mockito.when(userRepository.findByUsername(mockUser.getEmail())).thenReturn(mockUser);
        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        Mockito.when(resultRepository.getResultObject(mockQuiz.getId(), mockUser.getId())).thenReturn(mockResult);
        Mockito.when(resultRepository.getResultObject(mockQuiz.getId(), mockUser.getId())).thenReturn(mockResult);
        Mockito.when(resultRepository.save(mockResult)).thenReturn(mockResult);
        assertEquals(ResponseEntity.ok(mockResult), resultService.updateResult(mockQuiz.getId(), mockQuestion.getId(), mockSelectedOption, true));
    }

    @Test
    public void getResultByUserQuiz() throws Exception {
//        Mockito.when(resultService.getResultByUserQuiz(mockUser.getId(), mockQuiz.getId())).thenReturn(mockResult);
        Mockito.when(resultRepository.getResultByUserQuiz(mockUser.getId(), mockQuiz.getId())).thenReturn(mockResult);
        assertEquals(ResponseEntity.ok(mockResult), resultService.getResultByUserQuiz(mockUser.getId(), mockQuiz.getId()));
    }

    @Test
    public void submitQuiz() throws ResourceNotFoundException {
//        Mockito.when(resultService.submitQuiz(mockUser.getId(), mockQuiz.getId(), mockResult)).thenReturn(mockResult);
        Mockito.when(userRepository.findById(mockUser.getId())).thenReturn(Optional.ofNullable(mockUser));
        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        Mockito.when(resultRepository.insert(mockResult)).thenReturn(mockResult);
        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(Optional.ofNullable(mockQuestion));
        Mockito.when(questionService.validateAnswer(mockQuestion.getId(), mockQuestion.getAnswer())).thenReturn(true);
        assertEquals(ResponseEntity.ok(mockResult), resultService.submitQuiz(mockUser.getId(), mockQuiz.getId(), mockResult));
    }

    @Test
    public void getResultByUserQuizNotFound() throws Exception {
//        Mockito.when(resultService.getResultByUserQuiz(mockUser.getId(), mockQuiz.getId())).thenReturn(mockResult);
//        Mockito.when(resultRepository.getResultByUserQuiz(mockUser.getId(), mockQuiz.getId())).thenReturn(mockResult);
        assertEquals(ResponseEntity.ok(mockResult), resultService.getResultByUserQuiz(mockUser.getId(), mockQuiz.getId()));
    }
}