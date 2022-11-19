package com.example.demo_project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Bank;

@Repository  //Dao的目的是資料處理(CRUD) //宣告成Repository 讓Springboot去託管
@Transactional
public interface BankDao extends JpaRepository<Bank, String> {
	
	
	public void deleteByName(String name);
	
}
