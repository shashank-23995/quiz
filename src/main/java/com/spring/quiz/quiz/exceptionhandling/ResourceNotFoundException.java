package com.spring.quiz.quiz.exceptionhandling;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
