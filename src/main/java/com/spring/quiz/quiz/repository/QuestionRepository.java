package com.spring.quiz.quiz.repository;

import com.spring.quiz.quiz.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    @Query("{statement: ?0}")
    public Question getQuestionByStatement(String questionStatement);
}
