package com.example.group13backend.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SnowflakeUtilTest {
    @InjectMocks
    private SnowflakeUtil snowflakeUtil;

    @Test
    public void newId() {
        assertThat(snowflakeUtil.newId().toString().matches("[0-9]{18,}")).isTrue();
    }
}
