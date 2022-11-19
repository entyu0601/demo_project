package com.example.demo_project.vo;

import com.example.demo_project.entity.Bank;

public class BankResp { // 根據請求，會回應他所要的需求

	private Bank bank;

	private String message; // 回應是 做對還做錯!

	public BankResp() {

	}

	public BankResp(Bank bank, String message) {
		this.bank = bank;
		this.message = message;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
