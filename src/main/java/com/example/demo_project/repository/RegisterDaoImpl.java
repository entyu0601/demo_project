package com.example.demo_project.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo_project.entity.Register;

public class RegisterDaoImpl extends BaseDao {

	/*	do Query By Expired RegTime */
	public List<Register> doQueryByExpiredRegTime(Date date) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select R from Register R").append(" where R.regTime > :expiredDate"); // class裡面的屬性名字,Map的key值就是:後面的東西
		Map<String, Object> params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(), params, Register.class); // doQuery在BaseDao裡面，(String sql, Map<String, Object>
																// params, Class<EntityType> clazz)
	}

	/*	do Query With Page Size */
	public List<Register> doQueryByExpiredRegTime(Date date, int pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select R from Register R").append(" where R.regTime > :expiredDate"); // class裡面的屬性名字,Map的key值就是:後面的東西
		Map<String, Object> params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(), params, Register.class, pageSize); 
	}

	/*	do Query With page size and  Start Position */
	public List<Register> doQueryByExpiredRegTime(Date date, int pageSize, int startPosition) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select R from Register R").append(" where R.regTime > :expiredDate"); // class裡面的屬性名字,Map的key值就是:後面的東西
		Map<String, Object> params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(), params, Register.class, pageSize, startPosition);
	}
	
	/*	do Query With page size and  Start Position */ //裡面的語法就要用原生的sql語法-->就是取名都是table的名稱
	public List<Register> doNativeQueryByExpiredRegTime(Date date, int pageSize, int startPosition) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from register r").append(" where r.reg_time > :expiredDate"); // class裡面的屬性名字,Map的key值就是:後面的東西
		Map<String, Object> params = new HashMap<>();
		params.put("expiredDate", date);
		return doNativeQuery(sb.toString(), params, Register.class, pageSize, startPosition);
	}
	
}
