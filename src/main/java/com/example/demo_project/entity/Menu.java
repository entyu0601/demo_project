package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 跟springboot 宣告說這是一個實體類別(Bean)
@Table(name = "menu") //Entity 所要 對映的資料庫表
public class Menu {

	@Id
	@Column(name = "name")	//表的 欄位名稱
	private String name;

	@Column(name = "price")
	private Integer price;

	public Menu() {

	}

	public Menu(String name, Integer price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
