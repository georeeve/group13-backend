package com.example.group13backend.testutils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class TestUtil {
    public String getEndpoint(String path, int port) {
        return "http://localhost:" + port + "/api/v1" + path;
    }

    public HttpEntity<String> getAuthorizationEntity(String token) {
        final var httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);

        return new HttpEntity<>(httpHeaders);
    }
}
