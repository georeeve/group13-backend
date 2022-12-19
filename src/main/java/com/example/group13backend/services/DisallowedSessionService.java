package com.example.group13backend.services;

import com.example.group13backend.db.models.DisallowedSession;
import com.example.group13backend.db.repository.DisallowedSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisallowedSessionService {

  private final DisallowedSessionRepository disallowedSessionRepository;

  @Autowired
  public DisallowedSessionService(DisallowedSessionRepository disallowedSessionRepository) {
    this.disallowedSessionRepository = disallowedSessionRepository;
  }

  public void createDisallowedSession(DisallowedSession disallowedSession) {
    disallowedSessionRepository.save(disallowedSession);
  }

  public boolean checkDisallowedSession(Long id) {
    return disallowedSessionRepository.existsById(id);
  }
}
