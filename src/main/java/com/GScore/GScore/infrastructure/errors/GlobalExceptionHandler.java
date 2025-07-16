package com.GScore.GScore.infrastructure.errors;


import com.GScore.GScore.domain.exceptions.AuthenticationException;
import com.GScore.GScore.domain.exceptions.ExamResultNotFoundException;
import com.GScore.GScore.domain.exceptions.JwtInvalidException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception e) {
        ErrorResponse error = ErrorResponse.builder()
            .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
            .message(e.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(ExamResultNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExamResultNotFound(ExamResultNotFoundException ex) {
    ErrorResponse error = ErrorResponse.builder()
            .errorCode(ErrorCode.EXAM_RESULT_NOT_FOUND)
            .message(ex.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((error));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(JwtInvalidException.class)
    public ResponseEntity<ErrorResponse> handleJwtInvalidException(JwtInvalidException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(ErrorCode.JWT_TOKEN_INVALID)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException e) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .message("Failed to read file: " + e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException e) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .message("Database access error: " + e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}