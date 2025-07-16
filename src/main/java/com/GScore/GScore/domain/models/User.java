package com.GScore.GScore.domain.models;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
}
