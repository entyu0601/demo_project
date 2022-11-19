package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity				 //��spring boot ���ŧi�o�O�������O
@Table(name = "bank")//�s����Ʈw��XX��
public class Bank {

	@Id
	@Column(name = "account")
	private String account; // �b��

	@Column(name = "amount")
	private Integer amount; // �l�B
	
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
