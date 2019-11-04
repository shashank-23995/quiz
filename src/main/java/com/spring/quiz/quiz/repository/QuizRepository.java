package com.spring.quiz.quiz.repository;

import com.spring.quiz.quiz.model.Question;
import com.spring.quiz.quiz.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
    @Query(value="{quizId: ?0, 'questionSet.questionId': ?1}", fields = "{'questionSet.answer' : 1, _id: 0}")
    Question selectAnswer(String quizId, String questionId, String selectedOption);
}
