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
			System.out.println("�d�ߦW�٦C���o����");
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
				System.out.println(mapItem.getKey() + ": �d�L�����G!");
			} else {
				Product product = mapItem.getValue();
				System.out.println("�~���G"+product.getName()+ "  ����G" +product.getPrice()+ "  �w�s�ƶq�G" +product.getStorage());
			}
		}
	}

	@Override
	public void checkout(List<Product> wantproductList) {
		int total = 0;
		for (Product productItem : wantproductList) {
			if (productItem.getStorage() < productItem.getQuantity()) {
				System.out.println(productItem.getName() +": �s�w����!");
			} else {
				System.out.println("�~���G"+productItem.getName()+"  ����G"+productItem.getPrice()+"  �ʶR�ƶq�G"+productItem.getQuantity()+"  ���B:" +productItem.getPrice() * productItem.getQuantity());
				total += productItem.getPrice() * productItem.getQuantity();
			}
		}
		System.out.println("�`���B: " + total);
	}
}