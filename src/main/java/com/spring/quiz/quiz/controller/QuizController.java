package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Quiz quiz1 = quizService.createQuiz(quiz);
        if(quiz1!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(quiz1);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/createQuizWithQuestions", method = RequestMethod.POST)
    public ResponseEntity<Quiz> createQuizWithQuestions(@RequestBody Quiz quiz) throws Exception {
        Quiz quiz1 = quizService.createQuizWithQuestions(quiz);
        if (quiz1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(quiz1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/deleteQuiz/{quizId}", method = RequestMethod.DELETE)
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable String quizId){
        Quiz quiz = quizService.deleteQuiz(quizId);
        if (quiz != null){
            return ResponseEntity.status(HttpStatus.OK).body(quiz);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/updateQuiz/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@Valid @RequestBody Quiz quiz, @PathVariable String quizId){
        Quiz quiz1 = quizService.updateQuiz(quiz, quizId);
        if(quiz1 != null){
            return ResponseEntity.status(HttpStatus.OK).body(quiz1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/updateQuiz/quiz/{quizId}/addQuestion/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Quiz> addQuestion(@PathVariable String quizId, @PathVariable String questionId) throws ResourceNotFoundException {
        Quiz quiz = quizService.addQuestion(quizId, questionId);
        if (quiz != null) {
            return ResponseEntity.status(HttpStatus.OK).body(quiz);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
