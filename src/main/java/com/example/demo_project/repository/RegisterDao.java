package com.example.demo_project.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Register;

@Repository
public interface RegisterDao extends JpaRepository<Register, String>{
	
	@Transactional
	@Modifying
	@Query(" update Register reg set name = :newName, age = :newAge, city = :newCity, regTime = :newRegTime " + " where account = :account " )
	//寫這個語法所用的是entity的資料不是table的，所以在後面的名稱都是在entity裡面的命名。不是table的命名，[regTime = :newRegTime]-->:後面名稱可自取
	public int updateRegisterInfo(
			@Param("newName") String newName, //@Param("newName")字串裡的值，就是:後面的值。 String後的命名也是自取
			@Param("newAge") int newAge,
			@Param("newCity") String newCity,
			@Param("newRegTime") Date newRegTime,
			@Param("account") String account
			);
	
	public List<Register> doQueryByExpiredRegTime(Date date);

	public List<Register> doQueryByExpiredRegTime(Date date, int pageSize);
	
	public List<Register> doQueryByExpiredRegTime(Date date, int pageSize, int startPosition);
	
	public List<Register> doNativeQueryByExpiredRegTime(Date date, int pageSize, int startPosition);
}
