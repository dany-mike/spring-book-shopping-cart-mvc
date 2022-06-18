package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    User findByEmail(String email);

    public User findByResetPasswordToken(String token);
}