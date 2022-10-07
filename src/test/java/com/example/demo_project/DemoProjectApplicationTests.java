package com.example.demo_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo_project.entity.Bird;
import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.Active;
import com.example.demo_project.service.ifs.PersonService;

@SpringBootTest
class DemoProjectApplicationTests {

	@Autowired // 加上@Autowired，讓他實體化。
	private PersonService personService;

	@Test
	public void contextLoads() {
		Person person = personService.getPersonInfo("gogo");	
		System.out.println(person.getId());
    }

	@Autowired
	private Active birdActive;
	
	@Test
	public void activetest() {
		Bird bird = birdActive.fly("Willy", 10);
		System.out.println(bird.getName());
		System.out.println(bird.getAge());
		
	}
}
