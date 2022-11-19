package com.example.demo_project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo_project.controller.BankController;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.entity.Calculator;
import com.example.demo_project.entity.Calculator2;
import com.example.demo_project.entity.Menu;
import com.example.demo_project.entity.Person;
import com.example.demo_project.entity.Product;
import com.example.demo_project.service.ifs.BankService;
import com.example.demo_project.service.ifs.CalculatorService;
import com.example.demo_project.service.ifs.CalculatorService2;
import com.example.demo_project.service.ifs.MenuService;
import com.example.demo_project.service.ifs.PersonService;
import com.example.demo_project.service.ifs.ShoppingService;
import com.example.demo_project.vo.BankReq;
import com.example.demo_project.vo.BankResp;
import com.example.demo_project.vo.DepositReq;

import com.example.demo_project.vo.PersonReq2;
import com.example.demo_project.vo.PersonResp;
import com.example.demo_project.vo.WithdrawReq;

@WebAppConfiguration
@SpringBootTest
public class DemoProjectApplicationTests {

	@Autowired
	private PersonService personService;

//	@Test
//	public PersonResp getPersonInfoByAgeLargerThan(@RequestBody PersonReq2 req2) {
////		List<Person> getlist = personService.getPersonInfoByAgeLargerThan(req2.getAge());
////		PersonResp resp = new PersonResp(getlist);
//		return null;
//	}

//	@Autowired
//	private MenuService orderService;
//
//	@Test
//	public void addMenutest() {
//		Menu beefMenu = new Menu("beef", 100);
//		Menu porkMenu = new Menu("pork", 90);
//		Menu chickenMenu = new Menu("chicken", 80);
//		orderService.addMenu(beefMenu);
//		orderService.addMenu(porkMenu);
//		orderService.addMenu(chickenMenu);
//
//	}

//	@Autowired
//	private OrderController orderController;
//	
//	@Test
//	public void orderControllertest() {
//		FindfoodReq req = new FindfoodReq();
//		req.setFoodname("");
//		OrderResp resp = orderController.findMenuPrice(req);
//		System.out.println(resp.getFoodname());
//		System.out.println(resp.getPrice());
//		System.out.println(resp.getMessage());
//	}
//	

//
//	@Test
//	public void getTotalpriceTest() {
//		Menu beefMenu = new Menu("beef", 100);
//		Menu porkMenu = new Menu("pork", 90);
//		Menu chickenMenu = new Menu("chicken", 80);
//		Map<Menu, Integer> menuOrder = new HashMap<>();
//		menuOrder.put(beefMenu, 3);
//		menuOrder.put(porkMenu, 4);
//		menuOrder.put(chickenMenu, 5);
//		orderService.getTotalprice(menuOrder);
//	}
//	

//	@Autowired
//	private BankService bankService;

//	@Test
//	public void Banktest() {
//		Bank bank = new Bank();
//		bankService.getAmount(bank);
//		bankService.deposit(bank, 1000);
//		bankService.withdraw(bank, 2000);
//	}

//	@Autowired
//	private BankController bankController;
//
//	@Test
//	public void BankControllertest() {
//
////		BankReq req = new BankReq();
////		req.setAccount("A");
////		BankResp resp = bankController.getAmount(req);
////		System.out.println(resp.getBank().getAccount());
////		System.out.println(resp.getBank().getAmount());
////		System.out.println(resp.getMessage());
//
//		DepositReq req = new DepositReq();
//		BankResp resp = bankController.deposit(req);
//		System.out.println("您的帳戶為: " + resp.getBank().getAccount());
//		System.out.println("您的餘額為: " + resp.getBank().getAmount());
//		System.out.println(resp.getMessage());
//
//		WithdrawReq req1 = new WithdrawReq();
//		WithdrawResp resp1 = bankController.withdraw(req1);
//		System.out.println("您的帳戶為: " + resp1.getAccount());
//		System.out.println("您的餘額為: " + resp1.getAmount());
//		System.out.println(resp1.getMessage());
//	}
}

//	@Autowired
//	private CalculatorService calculatorService;
//
//	@Test
//	public void calculatetest() {
//		Calculator calculator = new Calculator();
//		calculatorService.runrunrun(calculator);
//
//	}
