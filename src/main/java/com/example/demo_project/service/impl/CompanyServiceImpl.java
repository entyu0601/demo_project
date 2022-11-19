package com.example.demo_project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Company;
import com.example.demo_project.entity.OrgCompanyId;
import com.example.demo_project.repository.CompanyDao;
import com.example.demo_project.service.ifs.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	public CompanyDao companyDao;

	@Override
	public List<Company> findAll() {
		List<Company> company = companyDao.findAll();
		return company;
	}

	@Override
	public Company findById(String orgId, String companyId) {
		OrgCompanyId OrgCompanyId = new OrgCompanyId(orgId, companyId); // 因為他的參數就是OrgCompanyId裡有的，所以就先把他叫出來用!!
		Optional<Company> compaOp = companyDao.findById(OrgCompanyId);
//		if (compaOp.isPresent()) {
////			Company company = compaOp.get();
////			return company;
//			return compaOp.get();
//		}
//		return new Company();
		return compaOp.orElse(new Company()); // (縮爆版)先用isPresent()確認有無值，有值就get()回傳，沒有的話就回傳(new Company())
	}

	@Override
	public Company updateById(String orgId, String companyId) {
		OrgCompanyId OrgCompanyId = new OrgCompanyId(orgId, companyId);
		Optional<Company> compaOp = companyDao.findById(OrgCompanyId);
		// update name
		if (compaOp.isPresent()) {
			Company com = compaOp.get();//jpa沒有提供update方法，所以只能先將他get出來再去update裡面的值再去save
			com.setCompanyId("A02"); // 將DB的A01資料改成A02 
			Company newcom = companyDao.save(com); // 要將資料save回去, 是save company的欄位優~
			return newcom;
		}

		return new Company();
	}

	@Override
	public Company saveCompany() {
		Company co = new Company("DDD", "D00", "jiji");
		return companyDao.save(co);
		
	}

}
