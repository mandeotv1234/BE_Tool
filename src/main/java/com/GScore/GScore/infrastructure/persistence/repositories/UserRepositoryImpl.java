package com.GScore.GScore.infrastructure.persistence.repositories;

import com.GScore.GScore.application.port.repositories.UserRepository;
import com.GScore.GScore.domain.models.User;
import com.GScore.GScore.infrastructure.persistence.entities.UserEntity;
import com.GScore.GScore.infrastructure.persistence.repositories.jpa.UserJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> userEntity = userJpaRepository.findByUsername(username);
        return userEntity.map(UserEntity::toModel);
    }

}
