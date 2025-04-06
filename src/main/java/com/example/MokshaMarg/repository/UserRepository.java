package com.example.MokshaMarg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MokshaMarg.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>	 findByEmail(String email);
}
