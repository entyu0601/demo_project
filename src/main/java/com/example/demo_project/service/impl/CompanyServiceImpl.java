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
		OrgCompanyId OrgCompanyId = new OrgCompanyId(orgId, companyId); // �]���L���ѼƴN�OOrgCompanyId�̦����A�ҥH�N����L�s�X�ӥ�!!
		Optional<Company> compaOp = companyDao.findById(OrgCompanyId);
//		if (compaOp.isPresent()) {
////			Company company = compaOp.get();
////			return company;
//			return compaOp.get();
//		}
//		return new Company();
		return compaOp.orElse(new Company()); // (�Y�z��)����isPresent()�T�{���L�ȡA���ȴNget()�^�ǡA�S�����ܴN�^��(new Company())
	}

	@Override
	public Company updateById(String orgId, String companyId) {
		OrgCompanyId OrgCompanyId = new OrgCompanyId(orgId, companyId);
		Optional<Company> compaOp = companyDao.findById(OrgCompanyId);
		// update name
		if (compaOp.isPresent()) {
			Company com = compaOp.get();//jpa�S������update��k�A�ҥH�u����N�Lget�X�ӦA�hupdate�̭����ȦA�hsave
			com.setCompanyId("A02"); // �NDB��A01��Ƨ令A02 
			Company newcom = companyDao.save(com); // �n�N���save�^�h, �Osave company������u~
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
