package com.grocery.GroceryApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery.GroceryApp.entity.Item;
import com.grocery.GroceryApp.entity.User;

@Repository
public interface UserGroceryRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
}
