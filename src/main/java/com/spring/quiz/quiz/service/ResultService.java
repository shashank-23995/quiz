package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.configuration.JwtRequestFilter;
import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import com.spring.quiz.quiz.model.Result;
import com.spring.quiz.quiz.model.User;
import com.spring.quiz.quiz.repository.QuizRepository;
import com.spring.quiz.quiz.repository.ResultRepository;
import com.spring.quiz.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    public List<Result> retrieveAllResult(){
        return resultRepository.findAll();
    }

    public  ResponseEntity<Result> getResultByUser(String userId, String quizId){
//        Optional<User> userOptional = userRepository.findById(userId);
//        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
//        if(userOptional.isPresent() && quizOptional.isPresent()){
//
//        }

        Result quizResult = resultRepository.getQuizResult(userId, quizId);
        return ResponseEntity.ok(quizResult);
    }

    public ResponseEntity<Result> selectAnswer(String quizId, String questionId, String selectedOption){
//        if(quizRepository.existsById(quizId)){
//            quizRepository.selectAnswer(quizId, questionId, selectedOption);
//            System.out.println("q = ");
//        }

        return ResponseEntity.ok().build();
    }

    public void updateResult(String quizId, String questionId, String selectedOption, boolean answerStatus) {
        User user = userRepository.findByUsername(JwtRequestFilter.token_username.get());
        Quiz quiz = new Quiz();
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if(optionalQuiz.isPresent()){
            quiz = optionalQuiz.get();
        }
        Result result = resultRepository.getResultObject(quizId, user.getId());
        HashMap<String, String> map = new HashMap<>();
        if(result == null){
            result = new Result();
        } else {
            map.putAll(result.getSelectedAnswer());
        }
        result.setUserId(user.getId());
        result.setQuizId(quizId);
//        map.put(questionId, selectedOption);
        if(map.put(questionId, selectedOption) == null && answerStatus){
            result.setObtainedMarks(result.getObtainedMarks() + 1);
        }
        result.setSelectedAnswer(map);
        result.setTotalMarks(quiz.getQuestionSet().size());
        resultRepository.save(result);
    }

    public ResponseEntity<Result> getResultByUserQuiz(String userId, String quizId){
        Result result = resultRepository.getResultByUserQuiz(userId, quizId);
        return ResponseEntity.ok(result);
//        return new ResponseEntity<>("result object will be displayed here", HttpStatus.NOT_FOUND);
    }
}
