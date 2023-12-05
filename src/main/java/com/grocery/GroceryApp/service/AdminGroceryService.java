package com.grocery.GroceryApp.service;

import java.util.List;

import com.grocery.GroceryApp.entity.Item;

public interface AdminGroceryService {

	public Item addNewItem(Item item);
	
	public List<Item> viewItems();
	
	public String removeItem(long id);
	
	public Item updateItem(Long id, Item item);
	
	public void manageInventory(Long productId, InventoryRequest request);
}
