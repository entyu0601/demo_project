package com.example.demo_project.service.ifs;

import java.util.List;
import com.example.demo_project.entity.Product;

public interface ShoppingService {
	public void queryProducts (List <String> nameList, List <Product> productList); //?~???d??
	public void checkout (List <Product> productList);	//???b
}
