package com.spring.quiz.quiz.repository;

import com.spring.quiz.quiz.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{email: ?0}")
    User findByUsername(String token_username);
//    public List<User> retrieveUsers();
//@Query(value = "{ 'userName' : ?0 }", fields = "{ 'requests': 1, '_id': 0 }")
//public List<User> getAllFriendRequests(String userName);
}
