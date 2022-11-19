package com.example.demo_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.service.ifs.BankService;
import com.example.demo_project.vo.BankReq;
import com.example.demo_project.vo.BankResp;

@RestController
public class BankController {

	@Autowired
	private BankService bankService;

	@PostMapping(value = "/api/getcreateAccount")
	public BankResp createAccount(@RequestBody BankReq request) throws Exception {
		return bankService.createAccount(request.getAccount());
	}

	@PostMapping(value = "/api/getAmount") // Http的方法
	public BankResp getAmount(@RequestBody BankReq request) { // RequestBody意思-->映對(Mapping)，外部JSON的東西Mapping屬性到這個class裡面
		return bankService.getAmount(request.getAccount());
	}

	@PostMapping(value = "/api/deposit")
	public BankResp deposit(@RequestBody BankReq request) {
		return bankService.deposit(request.getAccount(), request.getDeposit());
	}

	@PostMapping(value = "/api/withdraw")
	public BankResp withdraw(@RequestBody BankReq request) {
		return bankService.withdraw(request.getAccount(), request.getWithdraw());
	}

	@PostMapping(value = "/api/deleteAccount")
	public BankResp deleteAccount(@RequestBody BankReq request) throws Exception {
		bankService.deleteAccount(request.getAccount());
		return new BankResp(new Bank(), "Success");
	}

	@PostMapping(value = "/api/deleteName")
	public BankResp deleteName(@RequestBody BankReq request) throws Exception {
		bankService.deleteByName(request.getName());
		return new BankResp(new Bank(), "Success");
	}

}