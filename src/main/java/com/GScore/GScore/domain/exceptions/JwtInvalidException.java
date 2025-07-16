package com.GScore.GScore.domain.exceptions;

public class JwtInvalidException extends RuntimeException {
    public JwtInvalidException(String message) {
        super(message);
    }
}