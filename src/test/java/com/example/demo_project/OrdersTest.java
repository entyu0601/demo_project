package com.example.demo_project;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo_project.repository.OrdersDao;
import com.example.demo_project.vo.OrdersInfo;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes = DemoProjectApplication.class) // 是專案底下Application的名稱，並且要加.class
public class OrdersTest {

	@Autowired
	private OrdersDao ordersDao;

	@Test
	public void ordersInfoTest() throws JsonProcessingException {
		List<OrdersInfo> result = ordersDao.findAllOrdersInfo();
		for (OrdersInfo item : result) {
			System.out.println(item.getName());
		}

	}
}
