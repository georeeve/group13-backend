package com.example.group13backend.services;

import com.example.group13backend.db.models.Item;
import com.example.group13backend.db.repository.ItemRepository;
import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService {
  private final ItemRepository itemRepository;
  private final Logger logger;

  @Autowired
  public ItemService(ItemRepository itemRepository, Logger logger) {
    this.itemRepository = itemRepository;
    this.logger = logger;
  }

  public List<Item> getAllItems() {
    return itemRepository.findAll();
  }

  public Item getItemById(Long itemId) {
    Optional<Item> item = itemRepository.findById(itemId);
    if (item.isEmpty()) {
      logger.error(ErrorMessage.ITEM_NOT_FOUND);
      return null;
    }
    return item.get();
  }

  // ADMIN SERVICES
  @Transactional
  public void updateItemById(Long itemId, Item newItem) {
    Optional<Item> itemOptional = itemRepository.findById(itemId);
    if (itemOptional.isEmpty()) {
      logger.error(ErrorMessage.ITEM_NOT_FOUND);
      return;
    }

    Item oldItem = itemOptional.get();

    if (newItem.getName() != null
        && newItem.getName().length() > 0
        && !Objects.equals(oldItem.getName(), newItem.getName())) {
      oldItem.setName(newItem.getName());
    }

    if (newItem.getDescription() != null
        && newItem.getDescription().length() > 0
        && !Objects.equals(oldItem.getDescription(), newItem.getDescription())) {
      if (newItem.getDescription().length() > 200) {
        logger.error(ErrorMessage.DESCRIPTION_TOO_LONG);
        return;
      }
      oldItem.setDescription(newItem.getDescription());
    }

    if (newItem.getQuantity() != null
        && !Objects.equals(oldItem.getQuantity(), newItem.getQuantity())) {
      if (newItem.getQuantity() < 0) {
        logger.error(ErrorMessage.QUANTITY_INVALID);
      }
      oldItem.setQuantity(newItem.getQuantity());
    }

    if (newItem.getPrice() != null && !Objects.equals(oldItem.getPrice(), newItem.getPrice())) {
      if (newItem.getPrice() < 0) {
        logger.error(ErrorMessage.PRICE_INVALID);
      }
      oldItem.setPrice(newItem.getPrice());
    }
  }

  public void deleteItemById(Long itemId) {
    if (itemRepository.findById(itemId).isEmpty()) {
      logger.error(ErrorMessage.ITEM_NOT_FOUND);
      return;
    }
    itemRepository.deleteById(itemId);
  }
}
