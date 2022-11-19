package com.example.demo_project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Product;
import com.example.demo_project.service.ifs.ShoppingService;

@Service
public class ShoppingServiceImpl implements ShoppingService {

	@Override
	public void queryProducts(List<String> querynameList, List<Product> productList) {
		if (querynameList.isEmpty()) {
			System.out.println("琩高嘿ぃ眔");
			return;
		}
		
		Map<String, Product> queryMap = new HashMap<>();
		for (String nameItem : querynameList) {
			for (Product productItem : productList) {
				if (nameItem.equalsIgnoreCase(productItem.getName())) {
					queryMap.put(nameItem, productItem);
					break;
				} else {
					queryMap.put(nameItem, null);
				}
			}
		}
		
		for (Entry<String, Product> mapItem : queryMap.entrySet()) {
			if (mapItem.getValue() == null) {
				System.out.println(mapItem.getKey() + ": 琩礚挡狦!");
			} else {
				Product product = mapItem.getValue();
				System.out.println("珇兜"+product.getName()+ "  基" +product.getPrice()+ "  畐计秖" +product.getStorage());
			}
		}
	}

	@Override
	public void checkout(List<Product> wantproductList) {
		int total = 0;
		for (Product productItem : wantproductList) {
			if (productItem.getStorage() < productItem.getQuantity()) {
				System.out.println(productItem.getName() +": 畐ぃì!");
			} else {
				System.out.println("珇兜"+productItem.getName()+"  基"+productItem.getPrice()+"  潦禦计秖"+productItem.getQuantity()+"  肂:" +productItem.getPrice() * productItem.getQuantity());
				total += productItem.getPrice() * productItem.getQuantity();
			}
		}
		System.out.println("羆肂: " + total);
	}
}