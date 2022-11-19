package com.example.demo_project.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Person;
import com.example.demo_project.repository.PersonDao;
import com.example.demo_project.service.ifs.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	public PersonDao personDao;

	@Override
	public List<Person> getPersonInfo() {

		List<Person> perlist = personDao.findAll();
		return perlist;
	}

	@Override
	public Person getPersonInfoById(String id) {

//		Person person = personDao.findById(id).get(); // findById的Id 他是主鍵的意思喲!!不是代表ID!!
//		return person;

		Optional<Person> personOp = personDao.findById(id); // 不是陣列而是單一的話，會用Option 包起來
//		if (personOp.isPresent()) { // 前面用Optional 包起來的東西，它裡面是否 有/沒有 東西!!
////			Person per = new Person();   //如果有東西就去get他
////			System.out.println(per.getId());
////			System.out.println(per.getName());
////			System.out.println(per.getAge());
//			return personOp.get();
//		} else {
//			return new Person(); // 如果沒有就return 回去空的Person!!
//		}

//		return personOp.orElse(new Person());  //
		return personDao.findById(id).orElse(new Person()); // 各種縮縮縮

	}

	@Override
	public List<Person> getPersonInfoByAgeLargerThan(Integer age) {

//		List<Person> perlist = personDao.findAll();
//		List<Person> addlist = new ArrayList<>();
//		for (Person find : perlist) {
//			if (age < find.getAge()) {
//				addlist.add(find);
//			}
//		}
//		return addlist;

		List<Person> perlist = personDao.findByAgeGreaterThan(age); // 另外再Dao裡面寫了自定義方法!(有命名規則)
		return perlist;

	}

	@Override
	public List<Person> findByNameAndAge(String name, int age) {

		List<Person> agenamelist = personDao.findByNameAndAge(name, age);//名字跟年齡一樣
		return agenamelist;
		
//		List<Person> agegreaterlist =personDao.findByNameAndAgeGreaterThan(name, age);
//		return agegreaterlist;
		
		
	}
}