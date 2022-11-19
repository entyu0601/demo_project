package com.example.demo_project.vo;

import java.util.List;

import com.example.demo_project.entity.Person;

public class PersonResp {

	public List<Person> personlist;

	public PersonResp() {

	}

	public List<Person> getPersonlist() {
		return personlist;
	}

	public void setPersonlist(List<Person> personlist) {
		this.personlist = personlist;
	}

}
