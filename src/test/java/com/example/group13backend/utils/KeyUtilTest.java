package com.example.group13backend.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KeyUtilTest {
  @InjectMocks private KeyUtil keyUtil;

  @Test
  public void getPrivateKey() {
    assertThat(keyUtil.getPrivateKey()).isNotNull();
  }

  @Test
  public void getPublicKey() {
    assertThat(keyUtil.getPublicKey().toString()).contains("Sun EC public key");
  }
}
