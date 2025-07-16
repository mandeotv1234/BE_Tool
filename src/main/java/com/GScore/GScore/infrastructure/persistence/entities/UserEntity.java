package com.GScore.GScore.infrastructure.persistence.entities;

import com.GScore.GScore.domain.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    private String password;



    public User toModel() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

}
