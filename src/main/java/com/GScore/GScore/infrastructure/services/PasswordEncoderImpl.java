package com.GScore.GScore.infrastructure.services;


import com.GScore.GScore.application.port.services.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {
    private final org.springframework.security.crypto.password.PasswordEncoder encoder;

    public PasswordEncoderImpl(org.springframework.security.crypto.password.PasswordEncoder encoder){
        this.encoder = encoder;
    }

    @Override
    public String encode(String data) {
        return encoder.encode(data);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword){
        return encoder.matches(rawPassword, encodedPassword);
    }
}
