package com.example.demo_project.service.impl;

import com.example.demo_project.entity.Bird;
import com.example.demo_project.service.ifs.Active;

import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.PersonService;

@Service
public class BirdFlyImpl implements Active {
	
	@Override
	public Bird fly(String name, int age) {
		Bird bird = new Bird();
		bird.setName(name);
		bird.setAge(age);
		return bird;
	}
	
	public void printBirdAttributes(Bird bird) {
		System.out.println(bird.getName()+"正在飛");
		System.out.println("今年"+bird.getAge()+"歲");
		
		}

	
}
