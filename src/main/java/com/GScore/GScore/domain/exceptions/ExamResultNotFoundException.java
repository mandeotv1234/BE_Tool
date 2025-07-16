package com.GScore.GScore.domain.exceptions;

public class ExamResultNotFoundException extends RuntimeException {
    public ExamResultNotFoundException(String message) {
        super(message);
    }
}
