package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.Item;
import com.example.group13backend.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@ApiMapping("items")
public class ItemController {
    private final ItemServices itemServices;

    @Autowired
    public ItemController(ItemServices itemServices) { this.itemServices = itemServices; }

    @GetMapping
    public List<Item> getAllItems() { return  itemServices.geAllItems(); }

    @GetMapping(path = "{itemId}")
    public Optional<Item> getItemById(@PathVariable("itemId") Long itemId) { return itemServices.getItemById(itemId); }
}
