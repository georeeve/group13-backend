package com.example.group13backend.config;

import static java.time.Month.*;

import com.example.group13backend.db.models.Category;
import com.example.group13backend.db.repository.CategoryRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {
  @Bean
  CommandLineRunner categoryCommandLineRunner(CategoryRepository repository) {
    return args -> {
      Category produce = new Category("Produce");
      Category meatSeafood = new Category("Meat and Seafood");
      Category dairy = new Category("Dairy");
      Category frozenFoods = new Category("Frozen Foods");
      Category bakery = new Category("Bakery");
      Category beverages = new Category("Beverages");
      Category cannedGoods = new Category("Canned Goods and Dry Goods");
      Category condiments = new Category("Condiments and Sauces");
      Category personalCare = new Category("Personal Care and Household Items");
      Category snacks = new Category("Snacks and Candy");
      Category deli = new Category("Deli");
      Category international = new Category("International Foods");
      Category baby = new Category("Baby Products");
      Category pet = new Category("Pet Supplies");
      Category healthWellness = new Category("Health and Wellness");
      Category beauty = new Category("Beauty and Personal Care");
      Category flowers = new Category("Flowers");

      List<Category> categories =
          List.of(
              produce,
              meatSeafood,
              dairy,
              frozenFoods,
              bakery,
              beverages,
              cannedGoods,
              condiments,
              personalCare,
              snacks,
              deli,
              international,
              baby,
              pet,
              healthWellness,
              beauty,
              flowers);

      repository.saveAll(categories);
    };
  }
}
