package com.example.learnspringjwt.repositories;

import com.example.learnspringjwt.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByEmail(String email);
}
