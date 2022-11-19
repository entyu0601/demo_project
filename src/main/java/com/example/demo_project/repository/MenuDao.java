package com.example.demo_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Menu;

@Repository	//��Ʈw���q�������A�|�Q Service �I�s�A�z�L Dao(Data Access Object, ��Ʀs������)�ӹ�{���Ʈw���W�B�R�B��B�d
public interface MenuDao extends JpaRepository<Menu, String> {	//-->�~��Jpa����k <Entity, Entity�̪�@Id���ݩʫ��A>

}
