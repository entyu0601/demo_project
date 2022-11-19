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
			System.out.println("��" + count + "���A����3��");
			System.out.println("�ж�g�n�����B�⤽��: Add, Minus, Multi, Division, Mod");
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
				System.out.println("�п�J���T���B��r�A����");
				count++;
				continue;
			}

			System.out.println("�п�J��ơA�d��u��O2��20");
			try {
				num1 = sc.nextInt();
				number.setFirst_number(num1);
				num2 = sc.nextInt();
				number.setSceound_number(num2);
			} catch (Exception e) {
				System.out.println("�п�J�Ʀr�A����");
				count++;
				continue;
			}

			if ((num1 < 2 || num1 > 20) || (num2 < 2 || num2 > 20)) {
				System.out.println("���~! �п�J�d�򤺪��Ʀr�A����");
				count++;
				continue;
			}

			System.out.println("�⦡�O�_��:" + num1 + "" + oper + "" + num2 + ": �O�G�п�J(Y)�B�_�F�п�J(N)");
			check = sc.next();

			if (check.equalsIgnoreCase("Y")) {
				System.out.println(check + ":���T�A�~��B��");
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
								+ "�l�Ƭ�: "+total1);
						count++;
						break;
					} catch (Exception e) {
						System.out.println("�B�⥢�ѡA�S���l��");
						count++;
						continue;
					}
				}
			} else if (check.equalsIgnoreCase("N")) {
				System.out.println(check + ":�⦡���_�A����");
				count++;
				continue;
			} else {
				System.out.println("���~! �п�JY/N�A����");
				count++;
				continue;
			}
			break;
		}
	}
}
