package com.GScore.GScore.application.port.services;

public interface PasswordEncoder {
    String encode(String data);
    boolean matches(String rawPassword, String encodedPassword);
}
