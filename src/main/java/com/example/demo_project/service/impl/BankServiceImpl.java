package com.example.demo_project.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.repository.BankDao;
import com.example.demo_project.service.ifs.BankService;
import com.example.demo_project.vo.BankResp;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankDao;

//		@Transactional(rollbackOn = Exception.class) // 新增刪除修改DB
	@Override
	public BankResp createAccount(String account) {
		BankResp resp = new BankResp();
		Optional<Bank> bankOp = bankDao.findById(account);
		if (!StringUtils.hasText(account)) {
			resp.setMessage("帳戶不能為空!");
			return resp;
		}
		if (bankOp.isPresent()) {
			resp.setMessage("帳戶重複");
			return resp;
		}
		Bank bank = new Bank();
		bank.setAccount(account);
		bank.setAmount(0);

		resp.setBank(bank);
		resp.setMessage("帳戶創建成功!");
		bankDao.save(bank);
		return resp;
	}

	@Override
	public BankResp getAmount(String account) {
		BankResp resp = new BankResp();
		Optional<Bank> bankOp = bankDao.findById(account);
		if (bankOp.isPresent()) {
			Bank bank = bankOp.get();
			resp.setBank(bank);
			resp.setMessage("成功找到帳戶!");
			return resp;
		} else {
			resp.setMessage("找不到帳戶!");
			return resp;
		}
	}

	@Override
	public BankResp deposit(String account, int depositAmount) {

		BankResp resp = new BankResp();
		Optional<Bank> bankOp = bankDao.findById(account);
		if (bankOp.isPresent()) {
			if (depositAmount < 0) {
				resp.setMessage("存款不可小於0!");
				return resp;
			}
			Bank bank = bankOp.get();
			bank.setAmount(depositAmount + bank.getAmount());
			resp.setBank(bank);
			resp.setMessage("存款成功!");
			bankDao.save(bank);
			return resp;
		} else {
			resp.setMessage("找不到帳戶");
			return resp;
		}
	}

	@Override
	public BankResp withdraw(String account, int withdrawAmount) {

		BankResp resp = new BankResp();
		Optional<Bank> bankOp = bankDao.findById(account);
		if (bankOp.isPresent()) {
			if (withdrawAmount <= 0) {
				resp.setMessage("提款不可小於0");
				return resp;
			}
			Bank bank = bankOp.get();
			if (withdrawAmount > bank.getAmount()) {
				resp.setMessage("提款不可高於存款");
				return resp;
			}
			bank.setAmount(bank.getAmount() - withdrawAmount);
			resp.setBank(bank);
			resp.setMessage("提款成功!");
			bankDao.save(bank);
			return resp;
		} else {
			resp.setMessage("This Account doesn't exit!!");
			return resp;
		}
	}

	@Transactional // 事務
	@Override
	public void deleteAccount(String account) throws RuntimeException {
		bankDao.deleteById(account);
		System.out.println("Delete account success!!");
		throw new RuntimeException("Delete account fail!!");
	}

//	@Transactional
	@Override
	public void deleteByName(String name) throws RuntimeException {
		bankDao.deleteByName(name);
		System.out.println("Delete account success!!");
//			throw new RuntimeException("Delete account fail!!");

	}

}
