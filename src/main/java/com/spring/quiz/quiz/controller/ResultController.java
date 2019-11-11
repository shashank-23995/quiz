package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Result;
import com.spring.quiz.quiz.service.QuestionService;
import com.spring.quiz.quiz.service.QuizService;
import com.spring.quiz.quiz.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_CANDIDATE')")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/submitAnswer/quiz/{quizId}/question/{questionId}/answer/{selectedOption}", method = RequestMethod.GET)
    public ResponseEntity<Result> selectAnswer(@PathVariable String quizId, @PathVariable String questionId, @PathVariable String selectedOption) throws ResourceNotFoundException {
//        return resultService.selectAnswer(quizId, questionId, selectedOption);

//        quizService.findQuizQuestion(quizId, questionId);
        boolean answerStatus = questionService.validateAnswer(questionId, selectedOption);
        Result result = resultService.updateResult(quizId, questionId, selectedOption, answerStatus);
//        return ResponseEntity.ok().build();
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/user/{userId}/quiz/{quizId}", method = RequestMethod.GET)
    public ResponseEntity<Result> getResultByUserQuiz(@PathVariable String userId, @PathVariable String quizId) throws ResourceNotFoundException {
        Result result = resultService.getResultByUserQuiz(userId, quizId);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/submitQuiz/user/{userId}/quiz/{quizId}", method = RequestMethod.POST)
    public ResponseEntity<Result> submitQuiz(@PathVariable String userId, @PathVariable String quizId, @RequestBody Result result) throws ResourceNotFoundException {
        Result result1 = resultService.submitQuiz(userId, quizId, result);
        if (result1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
