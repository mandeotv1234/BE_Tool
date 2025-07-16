package com.GScore.GScore.application.port.repositories;

import com.GScore.GScore.domain.models.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

}
