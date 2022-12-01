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
		sb.append(" select R from Register R").append(" where R.regTime > :expiredDate"); // class�̭����ݩʦW�r,Map��key�ȴN�O:�᭱���F��
		Map<String, Object> params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(), params, Register.class); // doQuery�bBaseDao�̭��A(String sql, Map<String, Object>
																// params, Class<EntityType> clazz)
	}

	/*	do Query With Page Size */
	public List<Register> doQueryByExpiredRegTime(Date date, int pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select R from Register R").append(" where R.regTime > :expiredDate"); // class�̭����ݩʦW�r,Map��key�ȴN�O:�᭱���F��
		Map<String, Object> params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(), params, Register.class, pageSize); 
	}

	/*	do Query With page size and  Start Position */
	public List<Register> doQueryByExpiredRegTime(Date date, int pageSize, int startPosition) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select R from Register R").append(" where R.regTime > :expiredDate"); // class�̭����ݩʦW�r,Map��key�ȴN�O:�᭱���F��
		Map<String, Object> params = new HashMap<>();
		params.put("expiredDate", date);
		return doQuery(sb.toString(), params, Register.class, pageSize, startPosition);
	}
	
	/*	do Query With page size and  Start Position */ //�̭����y�k�N�n�έ�ͪ�sql�y�k-->�N�O���W���Otable���W��
	public List<Register> doNativeQueryByExpiredRegTime(Date date, int pageSize, int startPosition) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from register r").append(" where r.reg_time > :expiredDate"); // class�̭����ݩʦW�r,Map��key�ȴN�O:�᭱���F��
		Map<String, Object> params = new HashMap<>();
		params.put("expiredDate", date);
		return doNativeQuery(sb.toString(), params, Register.class, pageSize, startPosition);
	}
	
}
