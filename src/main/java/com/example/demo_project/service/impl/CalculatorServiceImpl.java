package com.example.demo_project.service.impl;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Calculator;
import com.example.demo_project.service.ifs.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	int count = 1;
	Scanner sc = new Scanner(System.in);
	String choose, oper, check;
	int num1, num2, total;

	@Override
	public void runrunrun(Calculator number) {
		// TODO Auto-generated method stub
		while (count <= 3) {
			System.out.println("==============");
			System.out.println("第" + count + "次，限制3次");
			System.out.println("請填寫要做的運算公式: Add, Minus, Multi, Division, Mod");
			choose = sc.next();

			if (choose.equalsIgnoreCase("Add")) {
				oper = "+";
			} else if (choose.equalsIgnoreCase("Minus")) {
				oper = "-";
			} else if (choose.equalsIgnoreCase("Multi")) {
				oper = "*";
			} else if (choose.equalsIgnoreCase("Division")) {
				oper = "/";
			} else if (choose.equalsIgnoreCase("Mod")) {
				oper = "%";
			} else {
				System.out.println("請輸入正確的運算字，結束");
				count++;
				continue;
			}

			System.out.println("請輸入兩數，範圍只能是2至20");
			try {
				num1 = sc.nextInt();
				number.setFirst_number(num1);
				num2 = sc.nextInt();
				number.setSceound_number(num2);
			} catch (Exception e) {
				System.out.println("請輸入數字，結束");
				count++;
				continue;
			}

			if ((num1 < 2 || num1 > 20) || (num2 < 2 || num2 > 20)) {
				System.out.println("錯誤! 請輸入範圍內的數字，結束");
				count++;
				continue;
			}

			System.out.println("算式是否為:" + num1 + "" + oper + "" + num2 + ": 是：請輸入(Y)、否；請輸入(N)");
			check = sc.next();

			if (check.equalsIgnoreCase("Y")) {
				System.out.println(check + ":正確，繼續運算");
				switch (oper) {
				case "+":
					total = (num1 + num2);
					System.out.printf(num1 + "+" + num2 +"="+total);
					count++;
					break;
				case "-":
					total = (num1 - num2);
					System.out.printf(num1 + "-" + num2 +"="+total);
					count++;
					break;
				case "*":
					total = (num1 * num2);
					System.out.printf(num1 + "*" + num2 +"="+total);
					count++;
					break;
				case "/":
					float f1 = number.getFirst_number();
					float f2 =number.getSceound_number();
					float total =f1 / f2;
					System.out.printf(f1 + "/" + f2 +"="+total);
					count++;
					break;
				case "%":
					try {
						int total1 = (num1 % num2);
						System.out.printf(num1 + "/" + num2 +" "
								+ "餘數為: "+total1);
						count++;
						break;
					} catch (Exception e) {
						System.out.println("運算失敗，沒有餘數");
						count++;
						continue;
					}
				}
			} else if (check.equalsIgnoreCase("N")) {
				System.out.println(check + ":算式為否，結束");
				count++;
				continue;
			} else {
				System.out.println("錯誤! 請輸入Y/N，結束");
				count++;
				continue;
			}
			break;
		}
	}
}
