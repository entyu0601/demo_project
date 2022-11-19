package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
	
	@Id//�L�O��Ʈw��PK�ҥH�~�n�[�W@I(�D��i�H���ܦh��)
	@Column(name = "id")//table�̪��W�r
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;

	public Person() {

	}

	public Person(String id, String name, int age) {
		this.name = name;
		this.id = id;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
