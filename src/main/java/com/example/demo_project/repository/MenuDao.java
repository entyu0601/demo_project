package com.example.demo_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Menu;

@Repository	//資料庫溝通的中介，會被 Service 呼叫，透過 Dao(Data Access Object, 資料存取物件)來實現對資料庫的增、刪、改、查
public interface MenuDao extends JpaRepository<Menu, String> {	//-->繼承Jpa的方法 <Entity, Entity裡的@Id的屬性型態>

}
