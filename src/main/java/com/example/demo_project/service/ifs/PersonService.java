package com.example.demo_project.service.ifs;

import com.example.demo_project.entity.Person;

public interface PersonService {
	public Person getPersonInfo(String id); //定義方法
	
//	default Person  getPersonInfo2() {		//定義一個default方法
//		return new Person();
//	}
}


//介面裡面是用來定義方法，所以裡面沒有實作。
//只有權限是 default才能去實作他
