package com.grocery.GroceryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.GroceryApp.entity.Order;

public interface OrderGroceryRepository extends JpaRepository<Order, Long>{

}
