package com.grocery.GroceryApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.GroceryApp.entity.Item;
import com.grocery.GroceryApp.service.AdminGroceryServiceImpl;
import com.grocery.GroceryApp.service.InventoryRequest;

@RestController
@RequestMapping("/grocery")
public class GroceryController {
	
	@Autowired
	private AdminGroceryServiceImpl adminGroceryService;
	
	@GetMapping("/home")
	public String homePage() {
		return "Welcome to Grocery management app";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addItem")
	public ResponseEntity<Item> addItemToBasket(@RequestBody Item item){
		
		Item addedItem = adminGroceryService.addNewItem(item);		
		return new ResponseEntity<Item>(addedItem, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteItem/{itemid}")
	public void deleteItemFromBasket(@PathVariable int itemId) {
		
		adminGroceryService.removeItem(itemId);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/viewItems")
	public List<Item> viewItems(){
		
		return adminGroceryService.viewItems();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/updateItem/{itemId}")
	public Item updateItem(@PathVariable long itemId, @RequestBody Item item){
		
		return adminGroceryService.updateItem(itemId, item);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{productId}/manage-inventory")
    public ResponseEntity<?> manageInventory(
            @PathVariable Long productId,
            @RequestBody InventoryRequest request) {
		adminGroceryService.manageInventory(productId, request);
        return ResponseEntity.ok().build();
    }
}
