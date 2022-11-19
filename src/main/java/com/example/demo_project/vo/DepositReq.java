package com.example.demo_project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepositReq {

	@JsonProperty("account")
	private String account;
	@JsonProperty("deposit")
	private int deposit;

	public DepositReq() {

	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
}
