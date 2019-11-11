package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/questions")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getAll")
    public List<Question> retrieveQuestions() {
        return questionService.retrieveQuestions();
    }

    @RequestMapping(value = "/createQuestion", method = RequestMethod.POST)
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question) throws ResourceNotFoundException{
        Question question1 = questionService.createQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(question1);
//         ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/deleteQuestion/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Question> deleteQuestion(@PathVariable String questionId) throws ResourceNotFoundException {
        Question question = questionService.deleteQuestion(questionId);
        return ResponseEntity.status(HttpStatus.OK).body(question);
//        return ResponseEntity.status(HttpStatus.resolve(200)).build();
    }

    @RequestMapping(value = "/updateQuestion/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Question> updateQuestion(@Valid @RequestBody Question question, @PathVariable String id) throws ResourceNotFoundException{
        Question question1 = questionService.updateQuestion(question, id);
        return ResponseEntity.status(HttpStatus.OK).body(question1);
//        return ResponseEntity.status(HttpStatus.resolve(200)).build();
    }

    @RequestMapping(value = "/statement/{questionStatement}", method = RequestMethod.GET)
    public ResponseEntity<Question> getQuestionByStatement(@PathVariable String questionStatement){
        Question question =  questionService.getQuestionByStatement(questionStatement);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }
}
