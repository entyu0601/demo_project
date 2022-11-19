package com.example.demo_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, String> { // <Entity, @Id的資料型態>
	
	//找資料大於輸入的年齡的話，回傳多個
	public List<Person> findByAgeGreaterThan(int age);		//方法不能亂寫，寫名方法遵照小駝峰規則! //至springboot-jpa找規則!!
	
	public List<Person> findByNameAndAge(String name, int age);//命名除了follow規則以外還要遵守DB的欄位名稱
	
	public List<Person> findByNameAndAgeGreaterThan(String name, int age);
	
	
	}
