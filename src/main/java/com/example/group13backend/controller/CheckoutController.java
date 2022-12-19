package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import com.example.group13backend.services.ItemService;
import com.example.group13backend.services.UserService;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiMapping("checkout")
public class CheckoutController {

  private final ItemService itemService;
  private final Logger logger;
  private final UserService userService;

  public CheckoutController(ItemService itemService, Logger logger, UserService userService) {
    this.itemService = itemService;
    this.logger = logger;
    this.userService = userService;
  }

  @PostMapping
  public void checkout(
      @RequestHeader("Authorization") String authorization,
      @RequestBody Map<Long, Integer> checkoutItems) {
    if (userService.getCurrentUser(authorization) != null) {
      checkoutItems.forEach(
          (id, number) -> {
            if (number < 1) {
              logger.error(ErrorMessage.QUANTITY_INVALID);
              return;
            }
            final var item = itemService.getItemById(id);
            if (item == null) {
              logger.error(ErrorMessage.ITEM_NOT_FOUND);
              return;
            }
            if ((item.getQuantity() - number) < 0) {
              logger.error(ErrorMessage.QUANTITY_INVALID);
            }
          });
      checkoutItems.forEach(
          (id, number) -> {
            final var item = itemService.getItemById(id);
            item.setQuantity(item.getQuantity() - number);
            itemService.updateItemById(id, item);
          });
    }
  }
}
