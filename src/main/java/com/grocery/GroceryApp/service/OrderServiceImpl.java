package com.grocery.GroceryApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.GroceryApp.entity.Item;
import com.grocery.GroceryApp.entity.User;
import com.grocery.GroceryApp.entity.Order;
import com.grocery.GroceryApp.entity.OrderItemRequest;
import com.grocery.GroceryApp.entity.OrderResponse;
import com.grocery.GroceryApp.entity.OrderItemResponse;
import com.grocery.GroceryApp.entity.OrderRequest;
import com.grocery.GroceryApp.exception.ResourceNotFoundException;
import com.grocery.GroceryApp.repository.ItemGroceryRepository;
import com.grocery.GroceryApp.repository.OrderGroceryRepository;
import com.grocery.GroceryApp.repository.UserGroceryRepository;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ItemGroceryRepository itemGroceryRepository;

    @Autowired
    private UserGroceryRepository userGroceryRepository;
    
    @Autowired
    private OrderGroceryRepository orderGroceryRepository;
	
	@Override
	public OrderResponse createOrder(String username, OrderRequest orderRequest) {
		User user = userGroceryRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        List<OrderItemResponse> orderItems = new ArrayList<>();
        Double totalOrderAmount = 0.0;
        
        Order order = new Order();
        order.setUser(user);

        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
        	Item item = itemGroceryRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemRequest.getProductId()));

            if (item.getQuantity() >= itemRequest.getQuantity()) {
            	item.setQuantity(item.getQuantity() - itemRequest.getQuantity());
            	itemGroceryRepository.save(item);

            	OrderItemRequest orderItemRequest = new OrderItemRequest();
            	orderItemRequest.setOrder(order);
            	orderItemRequest.setItem(item);
            	orderItemRequest.setQuantity(itemRequest.getQuantity());

                totalOrderAmount += item.getPrice() * itemRequest.getQuantity();

                order.getItems().add(orderItemRequest);

                OrderItemResponse orderItemResponse = new OrderItemResponse();
                orderItemResponse.setProductId(itemRequest.getProductId());
                orderItemResponse.setQuantity(itemRequest.getQuantity());
                orderItems.add(orderItemResponse);
            } else {
                throw new IllegalArgumentException("Insufficient inventory for product: " + itemRequest.getProductId());
            }
        }

        order.setTotalAmount(totalOrderAmount);
        
        Order savedOrder = orderGroceryRepository.save(order);
        
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(savedOrder.getId());
        orderResponse.setItems(orderItems);

        return orderResponse;
	}

}
