package com.example.demo_project.vo;

import java.util.List;

import com.example.demo_project.entity.Menu;

public class Order {//為了totalPrice，所以多設了一個Order的class

	private List<Menu> menuList;

	private int totalPrice;

	public Order() {

	}
	
	public Order(List<Menu> menuList, int totalPrice) {
		this.menuList=menuList;
		this.totalPrice=totalPrice;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
