package com.example.group13backend.db.repository;

import com.example.group13backend.db.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i JOIN i.category c ON i.category.id = c.id WHERE c.id = ?1")
    List<Item> findAllByCategoryId(Long categoryId);

//    SELECT i.*
//    FROM items i
//    JOIN categories c ON i.category_id = c.id
//    WHERE c.id = 1
}
