package com.spring.quiz.quiz.controller;

import com.spring.quiz.quiz.exceptionhandling.ResourceNotFoundException;
import com.spring.quiz.quiz.model.User;
import com.spring.quiz.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<User> retrieveUsers() {
        return userService.retrieveUsers();
    }

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return userService.welcomeMessage();
    }


    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) throws ResourceNotFoundException{
        return userService.createUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable String userId) throws ResourceNotFoundException {
        return userService.deleteUser(userId);
//        return ResponseEntity.status(HttpStatus.resolve(200)).build();
    }

    @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, String id) throws ResourceNotFoundException{
        return userService.updateUser(user, id);
//        return ResponseEntity.status(HttpStatus.resolve(200)).build();
    }
}
