package com.example.group13backend;

import com.example.group13backend.controller.CategoryController;
import com.example.group13backend.db.models.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTests {
    @LocalServerPort
    private int port;

    private final CategoryController categoryController;
    private final TestRestTemplate restTemplate;
    private final TestUtils testUtils;

    @Autowired
    public CategoryControllerTests(CategoryController categoryController, TestRestTemplate restTemplate, TestUtils testUtils) {
        this.categoryController = categoryController;
        this.restTemplate = restTemplate;
        this.testUtils = testUtils;
    }

    @Test
    public void contextLoads() {
        assertThat(categoryController).isNotNull();
    }

    @Test
    public void getCategory() throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        final var response = this.restTemplate.exchange(testUtils.getEndpoint("/categories/1", port), HttpMethod.GET, HttpEntity.EMPTY, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        final var items = objectMapper.readValue(response.getBody(), Item[].class);
        assertThat(items).allMatch(item -> item.getId() != null && item.getName() != null && item.getDescription() != null
                && item.getQuantity() != null && item.getPrice() != null);
    }
}
