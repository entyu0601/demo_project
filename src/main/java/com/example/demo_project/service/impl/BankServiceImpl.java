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

//		@Transactional(rollbackOn = Exception.class) // �s�W�R���ק�DB
	@Override
	public BankResp createAccount(String account) {
		BankResp resp = new BankResp();
		Optional<Bank> bankOp = bankDao.findById(account);
		if (!StringUtils.hasText(account)) {
			resp.setMessage("�b�ᤣ�ର��!");
			return resp;
		}
		if (bankOp.isPresent()) {
			resp.setMessage("�b�᭫��");
			return resp;
		}
		Bank bank = new Bank();
		bank.setAccount(account);
		bank.setAmount(0);

		resp.setBank(bank);
		resp.setMessage("�b��Ыئ��\!");
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
			resp.setMessage("���\���b��!");
			return resp;
		} else {
			resp.setMessage("�䤣��b��!");
			return resp;
		}
	}

	@Override
	public BankResp deposit(String account, int depositAmount) {

		BankResp resp = new BankResp();
		Optional<Bank> bankOp = bankDao.findById(account);
		if (bankOp.isPresent()) {
			if (depositAmount < 0) {
				resp.setMessage("�s�ڤ��i�p��0!");
				return resp;
			}
			Bank bank = bankOp.get();
			bank.setAmount(depositAmount + bank.getAmount());
			resp.setBank(bank);
			resp.setMessage("�s�ڦ��\!");
			bankDao.save(bank);
			return resp;
		} else {
			resp.setMessage("�䤣��b��");
			return resp;
		}
	}

	@Override
	public BankResp withdraw(String account, int withdrawAmount) {

		BankResp resp = new BankResp();
		Optional<Bank> bankOp = bankDao.findById(account);
		if (bankOp.isPresent()) {
			if (withdrawAmount <= 0) {
				resp.setMessage("���ڤ��i�p��0");
				return resp;
			}
			Bank bank = bankOp.get();
			if (withdrawAmount > bank.getAmount()) {
				resp.setMessage("���ڤ��i����s��");
				return resp;
			}
			bank.setAmount(bank.getAmount() - withdrawAmount);
			resp.setBank(bank);
			resp.setMessage("���ڦ��\!");
			bankDao.save(bank);
			return resp;
		} else {
			resp.setMessage("This Account doesn't exit!!");
			return resp;
		}
	}

	@Transactional // �ư�
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
