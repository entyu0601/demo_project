package com.example.demo_project.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	@PersistenceContext // JPA�M��������
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
		// ���L�C�����G����^������ //MAP��KEY�� �N�O�e��Query�ɡA[@Param("newCity") String newCity]-->
		// :�᭱���r��ҥH�OString,Object
		//222111

		Query query = entityManager.createQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			// �쥻�ϥ�entrySet �M������-->�Ĥ@�ؼg�k
//			for(Entry<String, Object> item :params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}

			// -->�ĤG�ؼg�k
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		return query.getResultList(); // ���p�G�n���Ĥ@����ƪ��ܴN�O�hget(0)�F getFirstResult()���ܡA�O���Ĥ@����ƪ���m
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int pageSize) {
		Query query = entityManager.createQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) { // ���n��pageSize���t��
			query.setMaxResults(pageSize); // �Y�����t�ƪ��ܡA�]�w-->�n�b���o���Ʀr���A�]�w���̤j��
		}
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int pageSize, int startPosition) { // ���C�����G����^������
		Query query = entityManager.createQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) { // ���n��pageSize���t��
			query.setMaxResults(pageSize); // �Y�����t�ƪ��ܡA�]�w-->�n�b���o�����(����)���A�]�w���̤j��
		}
		if (startPosition >= 0) {
			query.setFirstResult(startPosition); // �n�q���@�Ӧ�m�}�l�h���ڭn����ƪ�����m
		}
		return query.getResultList();
	}

	/* createNativeQuery */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doNativeQuery(String nativeSql, Map<String, Object> params,
			Class<EntityType> clazz, int pageSize, int startPosition) {

		Query query = null;
		if (clazz == null) {
			query = entityManager.createNativeQuery(nativeSql);
		} else {
			query = entityManager.createNativeQuery(nativeSql, clazz);
		}
		if (!CollectionUtils.isEmpty(params)) {
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) { // ���n��pageSize���t��
			query.setMaxResults(pageSize); // �Y�����t�ƪ��ܡA�]�w-->�n�b���o�����(����)���A�]�w���̤j��
		}
		if (startPosition >= 0) {
			query.setFirstResult(startPosition); // �n�q���@�Ӧ�m�}�l�h���ڭn����ƪ�����m
		}
		return query.getResultList();
	}

}
