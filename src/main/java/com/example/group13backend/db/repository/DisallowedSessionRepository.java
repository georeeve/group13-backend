package com.example.group13backend.db.repository;

import com.example.group13backend.db.models.DisallowedSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisallowedSessionRepository extends JpaRepository<DisallowedSession, Long> {}
