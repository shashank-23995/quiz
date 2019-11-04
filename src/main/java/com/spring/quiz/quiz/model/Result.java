package com.spring.quiz.quiz.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document
public class Result {
    @Id
    private String id;
    private String userId;
    private String quizId;
    private HashMap<String, String> selectedAnswer;
    private int obtainedMarks;
    private int totalMarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public HashMap<String, String> getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(HashMap<String, String> selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public int getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}
