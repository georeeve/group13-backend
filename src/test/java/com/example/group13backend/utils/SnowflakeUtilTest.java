package com.example.group13backend.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SnowflakeUtilTest {
  @InjectMocks private SnowflakeUtil snowflakeUtil;

  @Test
  public void newId() {
    assertThat(snowflakeUtil.newId().toString().matches("[0-9]{18,}")).isTrue();
  }
}
