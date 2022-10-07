package com.example.demo_project.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.PersonService;

@Service  //跟Spring說，他是一個服務 請幫我託管他。
public class PersonServiceImpl implements PersonService{

	@Override
	public Person getPersonInfo(String id) {
		Person person = new Person();
		person.setId(id);
		person.setName("Baby");
		person.setCity("TW");
		person.setAge(23);
		
		
		return person;
	}
	
	public void printPersonAttributes(Person person) {
		System.out.println(person.getId());
		System.out.println(person.getName());
		System.out.println(person.getCity());
		System.out.println(person.getAge());
		
		}
}
