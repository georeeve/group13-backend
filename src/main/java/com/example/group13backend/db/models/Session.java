package com.example.group13backend.db.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Sessions")
@Table(name = "Sessions")
public class Session {
  @Id private long id;
  private long userId;

  public Session() {}

  public Session(Long id, Long userId) {
    this.id = id;
    this.userId = userId;
  }

  public long getId() {
    return id;
  }

  public long getUserId() {
    return userId;
  }
}
