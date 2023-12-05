package com.grocery.GroceryApp.service;

import com.grocery.GroceryApp.entity.User;

public interface UserGroceryService {

	User createUser(User user);
    User getUserByUsername(String username);
}
