package com.grocery.GroceryApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.GroceryApp.entity.Item;
import com.grocery.GroceryApp.exception.ResourceNotFoundException;
import com.grocery.GroceryApp.repository.ItemGroceryRepository;
import com.grocery.GroceryApp.repository.UserGroceryRepository;

@Service
public class AdminGroceryServiceImpl implements AdminGroceryService{

	@Autowired
	private ItemGroceryRepository itemGroceryRepository;
	@Override
	public Item addNewItem(Item item) {
		
		return itemGroceryRepository.save(item);
	}

	@Override
	public List<Item> viewItems() {
		
		return itemGroceryRepository.findAll();
	}

	@Override
	public String removeItem(long itemId) {
		
		itemGroceryRepository.deleteById(itemId);
		return "Data deleted successfully";
	}
	
	@Override
	public Item updateItem(Long itemId, Item item) {
		Item existingProduct = getItemById(itemId);
		existingProduct.setName(item.getName());
        existingProduct.setPrice(item.getPrice());
        return itemGroceryRepository.save(existingProduct);
	}

	

	@Override
	public void manageInventory(Long itemId, InventoryRequest request) {
		Item item = getItemById(itemId);
		if ("increase".equals(request.getAction())) {
			item.setQuantity(item.getQuantity() + request.getQuantity());
        } else if ("decrease".equals(request.getAction())) {
            if (item.getQuantity() >= request.getQuantity()) {
            	item.setQuantity(item.getQuantity() - request.getQuantity());
            } else {
                throw new IllegalArgumentException("Insufficient inventory for product: " + itemId);
            }
        } else {
            throw new IllegalArgumentException("Invalid inventory action: " + request.getAction());
        }

		itemGroceryRepository.save(item);
		
	}
	
	private Item getItemById(Long itemId) {
		return itemGroceryRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemId));
	}

}
