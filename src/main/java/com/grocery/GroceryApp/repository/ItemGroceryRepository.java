package com.grocery.GroceryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery.GroceryApp.entity.Item;

@Repository
public interface ItemGroceryRepository extends JpaRepository<Item, Long> {

}
