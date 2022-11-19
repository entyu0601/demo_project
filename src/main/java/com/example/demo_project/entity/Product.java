package com.example.demo_project.entity;

public class Product {
	private String name;	//品項
	private int price;		//價格
	private int quantity;	//購買數量
	private int storage;	//庫存
	
	public Product(String name,int price,int storage) {
		this.name=name;
		this.price=price;
		this.storage = storage;
	}
	
	public Product(int quantity) {
		this.quantity= quantity;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}

}
