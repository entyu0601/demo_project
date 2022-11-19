package com.example.demo_project.service.ifs;

import java.util.List;

import com.example.demo_project.entity.Person;

public interface PersonService {

	public List<Person> getPersonInfo(); // 取全部(3個)

	public Person getPersonInfoById(String id); // 帶入Id找到個人資料

	public List<Person> getPersonInfoByAgeLargerThan(Integer age);// 輸入Age找到比輸入的年齡還大的人
	
	public List<Person> findByNameAndAge(String name, int age);
}
