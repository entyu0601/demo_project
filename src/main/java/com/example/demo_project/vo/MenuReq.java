package com.example.demo_project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuReq {

	@JsonProperty("name") // 用於外部識別化(可寫可不寫，但寫會比較好!)
	private String name;

	@JsonProperty("price")
	private Integer price;

	@JsonProperty("quantity")
	private Integer quantity;

	public MenuReq() {

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
