package com.example.demo_project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonReq {

	@JsonProperty("id")
	private String id;

	public PersonReq() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
