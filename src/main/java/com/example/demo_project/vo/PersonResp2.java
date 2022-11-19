package com.example.demo_project.vo;

import java.util.List;

import com.example.demo_project.entity.Person;

public class PersonResp2 {

	public List<Person> addlist;

	public PersonResp2() {

	}

	public PersonResp2(List<Person> addlist) {
		this.addlist = addlist;
	}

	public List<Person> getAddlist() {
		return addlist;
	}

	public void setAddlist(List<Person> addlist) {
		this.addlist = addlist;
	}

}
