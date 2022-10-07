package com.example.demo_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo_project.entity.Bird;
import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.PersonService;
import com.example.demo_project.service.impl.BirdFlyImpl;
import com.example.demo_project.service.impl.PersonServiceImpl;

@SpringBootApplication
public class DemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
		PersonServiceImpl personservive = new PersonServiceImpl();
		Person person =personservive.getPersonInfo("Justin Bieber");
		personservive.printPersonAttributes(person);
	
//		public static void main(String[] args) {
//			BirdFlyImpl birdflying = new BirdFlyImpl();
//			Bird bird = birdflying.fly("Yummy", 10);
//			birdflying.printBirdAttributes(bird);
	}

}
