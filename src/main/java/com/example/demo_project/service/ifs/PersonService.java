package com.example.demo_project.service.ifs;

import java.util.List;

import com.example.demo_project.entity.Person;

public interface PersonService {

	public List<Person> getPersonInfo(); // ������(3��)

	public Person getPersonInfoById(String id); // �a�JId���ӤH���

	public List<Person> getPersonInfoByAgeLargerThan(Integer age);// ��JAge�����J���~���٤j���H
	
	public List<Person> findByNameAndAge(String name, int age);
}
