package com.example.group13backend.services;

import com.example.group13backend.db.models.Category;
import com.example.group13backend.db.models.Item;
import com.example.group13backend.db.repository.CategoryRepository;
import com.example.group13backend.db.repository.ItemRepository;
import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CategoryServices {

  private final CategoryRepository categoryRepository;
  private final ItemRepository itemRepository;
  private final Logger logger;

  public CategoryServices(
      CategoryRepository categoryRepository, ItemRepository itemRepository, Logger logger) {
    this.categoryRepository = categoryRepository;
    this.itemRepository = itemRepository;
    this.logger = logger;
  }

  public List<Item> getItemsByCategory(Long categoryId) {
    Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
    if (categoryOptional.isEmpty()) {
      logger.error(ErrorMessage.CATEGORY_NOT_FOUND);
      return null;
    }
    return itemRepository.findAllByCategoryId(categoryId);
  }

  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }
}
