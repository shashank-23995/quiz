package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.repository.QuestionRepository;
import com.spring.quiz.quiz.repository.QuizRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class QuizServiceTest {

    @InjectMocks
    QuizService quizService;

    @Mock
    QuizRepository quizRepository;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    Question question;

    @Mock
    Quiz quiz;

    Quiz mockQuiz;
    ArrayList<Quiz> mockQuizList;
    Question mockQuestion;
    Set<Question> mockQuestionSet;

    @Before
    public void setup() {
        mockQuestion = new Question("12345", "test_statement", "test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_option_1");
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
        Mockito.when(quizService.createQuiz(mockQuiz)).thenReturn(mockQuiz);
        assertEquals(mockQuiz, quizService.createQuiz(mockQuiz));
    }

    @Test
    public void deleteQuiz() {
        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        assertEquals(mockQuiz, quizService.deleteQuiz(mockQuiz.getId()));
//        Mockito.verify(quizService, Mockito.times(1)).deleteQuiz(mockQuiz.getId());
    }

    @Test
    public void updateQuiz() {
        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        assertEquals(mockQuiz, quizService.updateQuiz(mockQuiz, mockQuiz.getId()));
    }

    @Test
    public void addQuestion() throws ResourceNotFoundException {
        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(Optional.ofNullable(mockQuestion));
        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        Mockito.when(quizRepository.save(quiz)).thenReturn(mockQuiz);
        assertEquals(mockQuiz, quizService.addQuestion(mockQuiz.getId(), mockQuestion.getId()));
    }

    @Test
    public void findQuizQuestion() throws ResourceNotFoundException {

        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(Optional.ofNullable(mockQuestion));
        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        assertEquals(true, quizService.findQuizQuestion(mockQuiz.getId(), mockQuestion.getId()));
    }

    @Test
    public void createQuizWithQuestions() throws Exception {
        Boolean questionFlag = true;
//        Mockito.when(quizService.createQuizWithQuestions(mockQuiz)).thenReturn((mockQuiz));
        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(Optional.ofNullable(mockQuestion));
        Mockito.when(quizRepository.insert(mockQuiz)).thenReturn(mockQuiz);
        assertEquals(mockQuiz, quizService.createQuizWithQuestions(mockQuiz));
    }

    @Test
    public void createQuizNotFound() throws ResourceNotFoundException {
        Quiz mockQuiz1 = new Quiz();
        mockQuiz1.setId("12345");
        mockQuiz1.setName("");
        mockQuiz1.setQuestionSet(mockQuestionSet);
        Mockito.when(quizService.createQuiz(mockQuiz1)).thenReturn(mockQuiz1);
        assertEquals(ResponseEntity.ok(mockQuiz1), quizService.createQuiz(mockQuiz1));
    }

    @Test
    public void deleteQuizNotFound() {
//        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        assertEquals(mockQuiz, quizService.deleteQuiz(mockQuiz.getId()));
//        Mockito.verify(quizService, Mockito.times(1)).deleteQuiz(mockQuiz.getId());
    }

    @Test
    public void updateQuizNotFound() {
//        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        assertEquals(mockQuiz, quizService.updateQuiz(mockQuiz, mockQuiz.getId()));
    }

    @Test
    public void addQuestionNotFound() throws ResourceNotFoundException {
        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(Optional.ofNullable(mockQuestion));
//        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        Mockito.when(quizRepository.save(quiz)).thenReturn(mockQuiz);
        assertEquals(mockQuiz, quizService.addQuestion(mockQuiz.getId(), mockQuestion.getId()));
    }

    @Test
    public void findQuizQuestionNotFound() throws ResourceNotFoundException {
//        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        assertEquals(true, quizService.findQuizQuestion(mockQuiz.getId(), mockQuestion.getId()));
    }

    @Test
    public void findQuizQuestionQestionNotFound() throws ResourceNotFoundException {

//        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(Optional.ofNullable(mockQuestion));
        Mockito.when(quizRepository.findById(mockQuiz.getId())).thenReturn(Optional.ofNullable(mockQuiz));
        assertEquals(true, quizService.findQuizQuestion(mockQuiz.getId(), mockQuestion.getId()));
    }

    @Test
    public void createQuizWithQuestionsStatementEmpty() throws Exception {
        Question mockQuestion1 = new Question("12345", "", "test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_option_1");
        HashSet mockQuestionSet1 = new HashSet<>();
        mockQuestionSet1.add(mockQuestion1);

        Quiz mockQuiz1 = new Quiz();
        mockQuiz1.setId("12345");
        mockQuiz1.setName("test_name");
        mockQuiz1.setQuestionSet(mockQuestionSet1);
//        Mockito.when(quizService.createQuizWithQuestions(mockQuiz)).thenReturn((mockQuiz));
        Mockito.when(questionRepository.findById(mockQuestion1.getId())).thenReturn(Optional.ofNullable(mockQuestion1));
        Mockito.when(quizRepository.insert(mockQuiz1)).thenReturn(mockQuiz1);
        assertEquals(mockQuiz1, quizService.createQuizWithQuestions(mockQuiz1));
    }

    @Test
    public void createQuizWithQuestionsInvalidAnswer() throws Exception {
        Question mockQuestion1 = new Question("12345", "test_statement", "test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_answer");
        HashSet mockQuestionSet1 = new HashSet<>();
        mockQuestionSet1.add(mockQuestion1);

        Quiz mockQuiz1 = new Quiz();
        mockQuiz1.setId("12345");
        mockQuiz1.setName("test_name");
        mockQuiz1.setQuestionSet(mockQuestionSet1);
//        Mockito.when(quizService.createQuizWithQuestions(mockQuiz)).thenReturn((mockQuiz));
        Mockito.when(questionRepository.findById(mockQuestion1.getId())).thenReturn(Optional.ofNullable(mockQuestion1));
        Mockito.when(quizRepository.insert(mockQuiz1)).thenReturn(mockQuiz1);
        assertEquals(mockQuiz1, quizService.createQuizWithQuestions(mockQuiz1));
    }

    @Test
    public void createQuizWithQuestionsQuizNameEmpty() throws Exception {
        Question mockQuestion1 = new Question("12345", "test_statement", "test_option_1", "test_option_2", "test_option_3", "test_option_4", "test_option_1");
        HashSet mockQuestionSet1 = new HashSet<>();
        mockQuestionSet1.add(mockQuestion1);

        Quiz mockQuiz1 = new Quiz();
        mockQuiz1.setId("12345");
        mockQuiz1.setName("");
        mockQuiz1.setQuestionSet(mockQuestionSet1);
//        Mockito.when(quizService.createQuizWithQuestions(mockQuiz)).thenReturn((mockQuiz));
        Mockito.when(questionRepository.findById(mockQuestion1.getId())).thenReturn(Optional.ofNullable(mockQuestion1));
        Mockito.when(quizRepository.insert(mockQuiz1)).thenReturn(mockQuiz1);
        assertEquals(mockQuiz1, quizService.createQuizWithQuestions(mockQuiz1));
    }

    @Test
    public void createQuizWithQuestionsQuestionNotFound() throws Exception {
        Boolean questionFlag = true;
//        Mockito.when(quizService.createQuizWithQuestions(mockQuiz)).thenReturn((mockQuiz));
//        Mockito.when(questionRepository.findById(mockQuestion.getId())).thenReturn(Optional.ofNullable(mockQuestion));
        Mockito.when(quizRepository.insert(mockQuiz)).thenReturn(mockQuiz);
        assertEquals(mockQuiz, quizService.createQuizWithQuestions(mockQuiz));
    }
}