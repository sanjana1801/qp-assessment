package com.grocery.GroceryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.GroceryApp.entity.OrderRequest;
import com.grocery.GroceryApp.entity.OrderResponse;
import com.grocery.GroceryApp.service.OrderService;

@RestController
public class OrderController {

	@Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody OrderRequest orderRequest) {
        String username = userDetails.getUsername();
        OrderResponse orderResponse = orderService.createOrder(username, orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }
}
