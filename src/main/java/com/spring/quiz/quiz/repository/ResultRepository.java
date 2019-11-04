package com.spring.quiz.quiz.repository;

import com.spring.quiz.quiz.model.Result;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends MongoRepository<Result, String> {
    @Query("{userId: ?0, quizId: ?1}")
    Result getQuizResult(String userId, String quizId);

    @Query("{quizId: ?0, userId: ?1}")
    Result getResultObject(String quizId, String userId);

    @Query("{userId: ?0, quizId: ?1}")
    Result getResultByUserQuiz(String userId, String quizId);
}
