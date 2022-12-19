package com.example.group13backend.db.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "DisallowedSessions")
@Table(name = "DisallowedSessions")
public class DisallowedSession {

  @Id private long id;

  public DisallowedSession() {}

  public DisallowedSession(Long id) {
    this.id = id;
  }
}
