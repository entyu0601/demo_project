package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
	
	@Id//他是資料庫的PK所以才要加上@I(主鍵可以有很多個)
	@Column(name = "id")//table裡的名字
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
