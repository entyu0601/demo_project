package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity				 //跟spring boot 說宣告這是實體類別
@Table(name = "bank")//連結資料庫的XX表
public class Bank {

	@Id
	@Column(name = "account")
	private String account; // 帳戶

	@Column(name = "amount")
	private Integer amount; // 餘額
	
	@Column(name = "name")
	private String name;

	public Bank() {

	}

	public Bank(String account) {
		this.account = account;
	}

	public Bank(String account, Integer amount) {
		this.account = account;
		this.amount = amount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
