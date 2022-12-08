package com.example.group13backend.db.repository;

import com.example.group13backend.db.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
        //SELECT * FROM USERS u WHERE u.email = 1
    Optional<User> findUsersByEmail(String email);
}
