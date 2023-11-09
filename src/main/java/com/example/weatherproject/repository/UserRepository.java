package com.example.weatherproject.repository;

import com.example.weatherproject.module.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
