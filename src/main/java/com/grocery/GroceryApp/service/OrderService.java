package com.grocery.GroceryApp.service;

import com.grocery.GroceryApp.entity.OrderRequest;
import com.grocery.GroceryApp.entity.OrderResponse;

public interface OrderService {

	OrderResponse createOrder(String username, OrderRequest orderRequest);
}
