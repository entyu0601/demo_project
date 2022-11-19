package com.example.demo_project;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo_project.entity.Product;
import com.example.demo_project.service.ifs.ShoppingService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShopTest {

	@Autowired
	private ShoppingService shoppingService;

	@Test
	public void shoppingtest() { // 品項、價格、庫存
		Product p1 = new Product("p1", 20, 10);
		Product p2 = new Product("p2", 50, 10);
		Product p3 = new Product("p3", 20, 10);
		System.out.println("======================");
		
		List<Product> productList = new ArrayList<>();
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);

		List<String> querynameList = new ArrayList<>();
		querynameList.add("p1");
		querynameList.add("p2");
		querynameList.add("p4");

		shoppingService.queryProducts(querynameList, productList);
		System.out.println("======================");
		
		p1.setQuantity(5);
		p2.setQuantity(6);
		p3.setQuantity(11);
		shoppingService.checkout(productList);
	}
}
