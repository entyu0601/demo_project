package com.example.demo_project.service.ifs;

import com.example.demo_project.vo.BankResp;

public interface BankService {

	public BankResp createAccount(String account) throws Exception;

	public BankResp getAmount(String account); // ���o�l�B

	public BankResp deposit(String account, int depositAmount);

	public BankResp withdraw(String account, int withdrawAmount);
	
	public void deleteAccount(String account) throws RuntimeException;
	
	public void deleteByName(String name);
}
