package com.example.demo_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.PersonService;
import com.example.demo_project.vo.PersonReq;
import com.example.demo_project.vo.PersonReq2;
import com.example.demo_project.vo.PersonReq3;
import com.example.demo_project.vo.PersonResp;
import com.example.demo_project.vo.PersonResp2;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;// 定義好的方法可以取的所有資料，從被託管Spring boot的Service裡呼叫出

	@PostMapping(value = "/api/getPerson")
	public PersonResp getPersonInfo() {
		PersonResp resp = new PersonResp();
//		List<Person> personlist = new ArrayList<>();
//		personlist = personService.getPersonInfo();
//		List<Person> personlist = personService.getPersonInfo();
		resp.setPersonlist(personService.getPersonInfo());
		return resp;
	}

	@PostMapping(value = "/api/getPersonInfoById")
	public Person getPersonInfoById(@RequestBody PersonReq req) {
//		Person person = personService.getPersonInfoById(req.getId());
		return personService.getPersonInfoById(req.getId());

	}

	@PostMapping(value = "/api/getLargerThan")
	public PersonResp2 getPersonInfoByAgeLargerThan(@RequestBody PersonReq2 req2) {
//		List<Person> addlist = personService.getPersonInfoByAgeLargerThan(req2.getAge());
//		PersonResp2 resp2 = new PersonResp2(addlist);

		PersonResp2 resp2 = new PersonResp2(personService.getPersonInfoByAgeLargerThan(req2.getAge()));
		return resp2;

//	@PostMapping(value = "/api/getLargerThan")	//老師版本
//	public List<Person> getPersonInfoByAgeLargerThan(@RequestBody PersonReq2 req2) {
//		List<Person> list = personService.getPersonInfoByAgeLargerThan(req2.getAge());
//		List<Person> getlist = new ArrayList<>();
//		getlist.addAll(list);
//		List<Person> getlist = personService.getPersonInfoByAgeLargerThan(req2.getAge());
//		return getlist;

	}
	
	@PostMapping(value = "/api/ByNameAndAge")
	public PersonResp2 findByNameAndAgeGreaterThan(@RequestBody PersonReq3 req3) {

		PersonResp2 resp2 = new PersonResp2(personService.findByNameAndAge(req3.getName(),req3.getAge()));
		return resp2;
	}
}
