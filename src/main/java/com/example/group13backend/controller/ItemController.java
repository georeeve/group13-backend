package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.Item;
import com.example.group13backend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiMapping("items")
public class ItemController {
  private final ItemService itemService;

  @Autowired
  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping
  public List<Item> getAllItems() {
    return itemService.getAllItems();
  }

  @GetMapping(path = "{itemId}")
  public Item getItemById(@PathVariable("itemId") Long itemId) {
    return itemService.getItemById(itemId);
  }

  // ADMIN ROUTES
  @PatchMapping(path = "{itemId}")
  public void updateItemById(@PathVariable("itemId") Long itemId, @RequestBody Item newItem) {
    itemService.updateItemById(itemId, newItem);
  }

  @DeleteMapping(path = "{itemId}")
  public void deleteItemById(@PathVariable("itemId") Long itemId) {
    itemService.deleteItemById(itemId);
  }

  // TODO: deleteByBatch
  //    @DeleteMapping(path = "delete")

  // TODO: deleteAllItems
}
