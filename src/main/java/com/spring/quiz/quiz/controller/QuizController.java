package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/quizzes")
@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_CANDIDATE')")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/all")
    public List<Quiz> retrieveQuizzes(){
        return quizService.retrieveQuizzes();
    }

    @RequestMapping(value = "/createQuiz", method = RequestMethod.POST)
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz)  throws ResourceNotFoundException {
        return quizService.createQuiz(quiz);
    }

    @RequestMapping(value = "/createQuizWithQuestions", method = RequestMethod.POST)
    public ResponseEntity<String> createQuizWithQuestions(@RequestBody Quiz quiz) throws ResourceNotFoundException{
        return quizService.createQuizWithQuestions(quiz);
    }

    @RequestMapping(value = "/deleteQuiz/{quizId}", method = RequestMethod.DELETE)
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable String quizId){
        return quizService.deleteQuiz(quizId);
    }

    @RequestMapping(value = "/updateQuiz/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@Valid @RequestBody Quiz quiz, @PathVariable String quizId){
        return quizService.updateQuiz(quiz, quizId);
    }

    @RequestMapping(value = "/updateQuiz/addQuestion/{quizId}/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Quiz> addQuestion(@PathVariable String quizId, @PathVariable String questionId){
        return quizService.addQuestion(quizId, questionId);
    }
}
