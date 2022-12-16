package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.Category;
import com.example.group13backend.db.models.Item;
import com.example.group13backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiMapping("categories")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<Category> getCategories() {
    return categoryService.getCategories();
  }

  @GetMapping(path = "/{categoryId}")
  public List<Item> getItemsByCategory(@PathVariable("categoryId") Long categoryId) {
    return categoryService.getItemsByCategory(categoryId);
  }
}
