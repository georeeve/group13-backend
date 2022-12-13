package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.Item;
import com.example.group13backend.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiMapping("items")
public class ItemController {
    private final ItemServices itemServices;

    @Autowired
    public ItemController(ItemServices itemServices) {
        this.itemServices = itemServices;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemServices.geAllItems();
    }

    @GetMapping(path = "{itemId}")
    public Item getItemById(@PathVariable("itemId") Long itemId) {
        return itemServices.getItemById(itemId);
    }

    // ADMIN ROUTES
    @PatchMapping(path = "{itemId}")
    public void updateItemById(
            @PathVariable("itemId") Long itemId,
            @RequestBody Item newItem
    ) {
        itemServices.updateItemById(itemId, newItem);
    }

    @DeleteMapping(path = "{itemId}")
    public void deleteItemById(@PathVariable("itemId") Long itemId) {
        itemServices.deleteItemById(itemId);
    }

    //TODO: deleteByBatch
//    @DeleteMapping(path = "delete")

    //TODO: deleteAllItems
}
