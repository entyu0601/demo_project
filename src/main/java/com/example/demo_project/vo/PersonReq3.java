package com.example.demo_project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonReq3 {

	@JsonProperty("name")
	private String name;

	@JsonProperty("age")
	private Integer age;

	public PersonReq3() {

	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
