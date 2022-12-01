package com.example.demo_project.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	@PersistenceContext // JPA專有的註釋
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
		// ↑無每次結果有返回的筆數 //MAP的KEY值 就是前面Query時，[@Param("newCity") String newCity]-->
		// :後面的字串所以是String,Object
		//222111

		Query query = entityManager.createQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			// 原本使用entrySet 遍歷取值-->第一種寫法
//			for(Entry<String, Object> item :params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}

			// -->第二種寫法
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		return query.getResultList(); // 那如果要取第一筆資料的話就是去get(0)； getFirstResult()的話，是指第一筆資料的位置
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
		if (pageSize > 0) { // 不要讓pageSize為負數
			query.setMaxResults(pageSize); // 若不為負數的話，設定-->要在取得的數字中，設定取最大值
		}
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int pageSize, int startPosition) { // ↑每次結果有返回的筆數
		Query query = entityManager.createQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) { // 不要讓pageSize為負數
			query.setMaxResults(pageSize); // 若不為負數的話，設定-->要在取得的比數(筆數)中，設定取最大值
		}
		if (startPosition >= 0) {
			query.setFirstResult(startPosition); // 要從哪一個位置開始去取我要的資料的的位置
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
		if (pageSize > 0) { // 不要讓pageSize為負數
			query.setMaxResults(pageSize); // 若不為負數的話，設定-->要在取得的比數(筆數)中，設定取最大值
		}
		if (startPosition >= 0) {
			query.setFirstResult(startPosition); // 要從哪一個位置開始去取我要的資料的的位置
		}
		return query.getResultList();
	}

}
