package com.example.group13backend.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Argon2UtilTest {
  @InjectMocks private Argon2Util argon2Util;

  @Test
  public void hash() {
    assertThat(argon2Util.hash("password")).startsWith("$argon2id$v=");
  }

  @Test
  public void verify() {
    final var hash = argon2Util.hash("password");
    assertThat(argon2Util.verify("password", hash)).isTrue();
    assertThat(argon2Util.verify("password1", hash)).isFalse();
  }
}
