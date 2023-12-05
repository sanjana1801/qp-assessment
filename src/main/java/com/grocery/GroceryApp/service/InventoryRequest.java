package com.grocery.GroceryApp.service;

public class InventoryRequest {

	private String action;
    private Integer quantity;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
