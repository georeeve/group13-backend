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
public class ItemServices {
    private final ItemRepository itemRepository;
    private final Logger logger;

    @Autowired
    public ItemServices(ItemRepository itemRepository, Logger logger) {
        this.itemRepository = itemRepository;
        this.logger = logger;
    }

    public List<Item> geAllItems() {
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

    @Transactional
    public void updateItemById(
            Long itemId,
            String name,
            String description,
            Integer quantity,
            Double price
    ) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isEmpty()) {
            logger.error(ErrorMessage.ITEM_NOT_FOUND);
            return;
        }

        Item item = itemOptional.get();
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(item.getName(), name)
        ) {
            item.setName(name);
        }

        if (description != null &&
                description.length() > 0 &&
                !Objects.equals(item.getDescription(), description)
        ) {
            if (description.length() > 200) {
                logger.error(ErrorMessage.DESCRIPTION_TOO_LONG);
                return;
            }
            item.setDescription(description);
        }


    }
}
