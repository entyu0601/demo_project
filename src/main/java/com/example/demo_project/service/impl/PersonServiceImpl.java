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

//		Person person = personDao.findById(id).get(); // findById��Id �L�O�D�䪺�N���!!���O�N��ID!!
//		return person;

		Optional<Person> personOp = personDao.findById(id); // ���O�}�C�ӬO��@���ܡA�|��Option �]�_��
//		if (personOp.isPresent()) { // �e����Optional �]�_�Ӫ��F��A���̭��O�_ ��/�S�� �F��!!
////			Person per = new Person();   //�p�G���F��N�hget�L
////			System.out.println(per.getId());
////			System.out.println(per.getName());
////			System.out.println(per.getAge());
//			return personOp.get();
//		} else {
//			return new Person(); // �p�G�S���Nreturn �^�h�Ū�Person!!
//		}

//		return personOp.orElse(new Person());  //
		return personDao.findById(id).orElse(new Person()); // �U���Y�Y�Y

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

		List<Person> perlist = personDao.findByAgeGreaterThan(age); // �t�~�ADao�̭��g�F�۩w�q��k!(���R�W�W�h)
		return perlist;

	}

	@Override
	public List<Person> findByNameAndAge(String name, int age) {

		List<Person> agenamelist = personDao.findByNameAndAge(name, age);//�W�r��~�֤@��
		return agenamelist;
		
//		List<Person> agegreaterlist =personDao.findByNameAndAgeGreaterThan(name, age);
//		return agegreaterlist;
		
		
	}
}