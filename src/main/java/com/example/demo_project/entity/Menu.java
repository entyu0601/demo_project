package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // ��springboot �ŧi���o�O�@�ӹ������O(Bean)
@Table(name = "menu") //Entity �ҭn ��M����Ʈw��
public class Menu {

	@Id
	@Column(name = "name")	//�� ���W��
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
