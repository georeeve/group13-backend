package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.Category;
import com.example.group13backend.db.models.Item;
import com.example.group13backend.services.CategoryServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiMapping("categories")
public class CategoryController {

  private final CategoryServices categoryServices;

  @Autowired
  public CategoryController(CategoryServices categoryServices) {
    this.categoryServices = categoryServices;
  }

  @GetMapping
  public List<Category> getCategories() {
    return categoryServices.getCategories();
  }

  @GetMapping(path = "/{categoryId}")
  public List<Item> getItemsByCategory(@PathVariable("categoryId") Long categoryId) {
    return categoryServices.getItemsByCategory(categoryId);
  }
}
