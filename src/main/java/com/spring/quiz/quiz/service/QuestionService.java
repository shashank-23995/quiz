package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> retrieveQuestions() {
        return questionRepository.findAll();
    }

    public ResponseEntity<Question> createQuestion(Question question) throws ResourceNotFoundException{
        try {
            if(question.getStatement() !="" && question.getOption1() !="" && question.getOption2() !="" && question.getOption3() !="" && question.getOption4() !=""){
                if(question.getOption1().equals(question.getAnswer()) || question.getOption2().equals(question.getAnswer()) || question.getOption3().equals(question.getAnswer()) || question.getOption4().equals(question.getAnswer())){
                    questionRepository.insert(question);
                    return ResponseEntity.ok(question);
                } else {
                    throw new ResourceNotFoundException("Answer does not match with options");
                }
            } else {
                throw new ResourceNotFoundException("Options cannot be empty");
            }
        } catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    public ResponseEntity<Question> deleteQuestion(String questionId) throws ResourceNotFoundException{
        try {
            Optional<Question> questionOptional = questionRepository.findById(questionId);
            if(questionOptional.isPresent()){
                Question question = questionOptional.get();
                questionRepository.deleteById(questionId);
                return ResponseEntity.ok(question);
            } else {
                throw new ResourceNotFoundException("Question not found");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    public ResponseEntity<Question> updateQuestion(Question question, String questionId) throws ResourceNotFoundException{
        try {
            Optional<Question> questionOptional = questionRepository.findById(questionId);
            if(questionOptional.isPresent()){
                if(question.getStatement() != "" && question.getOption1()!= "" && question.getOption2()!=""&& question.getOption3()!=""&&question.getOption4()!="" && question.getAnswer()!=""){
                    if(question.getAnswer().equals(question.getOption1()) || question.getAnswer().equals(question.getOption2()) || question.getAnswer().equals(question.getOption3()) || question.getAnswer().equals(question.getOption4())){
//                      question.setId(questionId);
                        questionRepository.save(question);
                    } else {
                        throw new ResourceNotFoundException("answer does not match with options");
                    }
                } else {
                    throw new ResourceNotFoundException("Options cannot be empty");
                }
                return ResponseEntity.status(200).build();
            } else {
                throw new ResourceNotFoundException("Question not found");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    public ResponseEntity<Question> getQuestionByStatement(String questionStatement){
        Question q = questionRepository.getQuestionByStatement(questionStatement);
        return ResponseEntity.ok(q);
    }

    public boolean validateAnswer(String questionId, String selectedOption) throws ResourceNotFoundException{
        try {
            Optional<Question> questionOptional = questionRepository.findById(questionId);
            if(questionOptional.isPresent()){
                Question q = questionOptional.get();
                if(q.getAnswer().equals(selectedOption)){
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException("Question not found");
        }
    }
}
