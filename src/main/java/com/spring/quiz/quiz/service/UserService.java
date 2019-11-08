package com.spring.quiz.quiz.service;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.User;
import com.spring.quiz.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public List<User> retrieveUsers() {
        System.out.println("Getting data from DB - " + userRepository.findAll());
        return userRepository.findAll();
    }

    public User createUser(User user) throws ResourceNotFoundException{
        try {
            if(user.getFirstName()!="" && user.getLastName()!="" && user.getEmail()!="" && user.getPassword()!="" && user.getRole()!=""){
                user.setPassword(encoder.encode(user.getPassword()));
                User user1 = userRepository.insert(user);
                return user1;
            } else {
                throw new ResourceNotFoundException("User data is incomplete");
            }
        } catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    public User deleteUser(String userId) throws ResourceNotFoundException{
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                userRepository.deleteById(userId);
//                return ResponseEntity.ok(user);
                return user;
            } else {
                throw new ResourceNotFoundException("User not found");
            }
////            throw new RuntimeException();
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
        } catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    public User updateUser(User user, String userId) throws ResourceNotFoundException{
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if(optionalUser.isPresent()){
                user.setId(userId);
                return userRepository.save(user);
            } else {
                throw new ResourceNotFoundException();
//            throw new RuntimeException();
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException("User not found");
        }
    }

    public String welcomeMessage() {
        return "Welcome to quiz application";
    }
}
