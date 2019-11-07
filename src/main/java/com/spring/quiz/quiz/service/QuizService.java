package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.repository.QuestionRepository;
import com.spring.quiz.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<Quiz> retrieveQuizzes(){
        return quizRepository.findAll();
    }

    public ResponseEntity<Quiz> createQuiz(Quiz quiz) throws ResourceNotFoundException{
        try {
            if(!quiz.getName().equals("")) {
                quizRepository.insert(quiz);
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Quiz name not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Quiz> deleteQuiz(String quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if(quizOptional.isPresent()){
            quizRepository.deleteById(quizId);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Quiz> updateQuiz(Quiz quiz, String quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if(quizOptional.isPresent()){
            quiz.setId(quizId);
            quizRepository.save(quiz);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Quiz> addQuestion(String quizId, String questionId) throws ResourceNotFoundException {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        try {
            if(optionalQuiz.isPresent() && optionalQuestion.isPresent()){
                Quiz quiz = optionalQuiz.get();
                Question question = optionalQuestion.get();
                Set<Question> quizQuestionSet = quiz.getQuestionSet();
                quizQuestionSet.add(question);
                quiz.setQuestionSet(quizQuestionSet);
//            q.setId(quizId);
                quizRepository.save(quiz);
                return ResponseEntity.status(200).build();
            } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                throw new ResourceNotFoundException("Either quiz id or question id is invalid");
            }
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    public boolean findQuizQuestion(String quizId, String questionId) throws ResourceNotFoundException{
        try {
            Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
            if(optionalQuiz.isPresent()){
                Quiz q = optionalQuiz.get();
                Optional<Question> questionOptional = questionRepository.findById(questionId);
                if(questionOptional.isPresent()){
                    Question q1 = questionOptional.get();
                    Set s = q.getQuestionSet();
                    if(s.contains(q1)){
                        return true;
                    }
                }
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException("Question not found");
        }
    return false;
    }

//    public ResponseEntity<String> createQuizWithQuestions(String quizName, String[] quizQuestionSet) throws ResourceNotFoundException {
    public ResponseEntity<Quiz> createQuizWithQuestions(Quiz quiz) throws Exception {
//        Set<Question> newQuestionSet=null;
        boolean questionFlag = true;
        try {
            if(!quiz.getName().equals("")) {
//                quizRepository.insert(quiz);
               Set<Question> quizQuestionSet= quiz.getQuestionSet();
               if (quizQuestionSet.size() < 10){
                   throw new Exception("minimum 10 questions must be provided to create quiz");
               }
                Iterator iterator = quizQuestionSet.iterator();
                while (iterator.hasNext()){
                    Question singleQuestion = (Question) iterator.next();
                    Optional<Question> optionalQuestion = questionRepository.findById(singleQuestion.getId());
                    if(optionalQuestion.isPresent()){
                        Question question = optionalQuestion.get();
                        if(question.getStatement() == "" || question.getOption1() == "" || question.getOption2()== "" || question.getOption3()== "" || question.getOption4()== "" || question.getAnswer() ==""){
                            throw new ResourceNotFoundException("Question data is incomplete");
                        } else {
                            if (!(question.getAnswer().equals(question.getOption1()) || question.getAnswer().equals(question.getOption2()) || question.getAnswer().equals(question.getOption3()) || question.getAnswer().equals(question.getOption4()))){
                                throw new ResourceNotFoundException("answer does not match any option");
                            }
                        }
                    } else {
                        questionFlag = false;
                    }
                }
                if(questionFlag){
                    quizRepository.insert(quiz);
                    return ResponseEntity.ok(quiz);
                } else {
                    throw new ResourceNotFoundException("One of the question is missing from the list");
                }
//                for(int questionNumber = 0; questionNumber < quizQuestionSet.length; questionNumber++){
//                    Optional<Question> optionalQuestion = questionRepository.findById(quizQuestionSet[questionNumber]);
//                    if(optionalQuestion.isPresent()){
//                        Question question = optionalQuestion.get();
//                        newQuestionSet.add(question);
//                    } else {
//                        throw new ResourceNotFoundException("One of the question is missing from the list");
//                    }
//                }
//                Quiz quiz = new Quiz();
//                quiz.setName(quizName);
//                quiz.setQuestionSet(newQuestionSet);
//                quizRepository.insert(quiz);
//                return ResponseEntity.ok("Quiz created successfully");
            } else {
                throw new ResourceNotFoundException("Quiz name not found");
            }
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException(exception.getMessage());
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
}
