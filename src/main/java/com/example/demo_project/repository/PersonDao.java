package com.example.demo_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, String> { // <Entity, @Id����ƫ��A>
	
	//���Ƥj���J���~�֪��ܡA�^�Ǧh��
	public List<Person> findByAgeGreaterThan(int age);		//��k����üg�A�g�W��k��Ӥp�m�p�W�h! //��springboot-jpa��W�h!!
	
	public List<Person> findByNameAndAge(String name, int age);//�R�W���Ffollow�W�h�H�~�٭n��uDB�����W��
	
	public List<Person> findByNameAndAgeGreaterThan(String name, int age);
	
	
	}
