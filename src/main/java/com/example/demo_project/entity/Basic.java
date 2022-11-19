package com.example.demo_project.entity;

public class Basic {

	private static int count = 0;
	public int serialNo=0;
	
	public Basic() {
		serialNo = ++ Basic.count;
	}
	
	public static void main(String[] args) {
		Basic b01 = new Basic();
		Basic b02 = new Basic();
		Basic b03 = new Basic();
		
		//印出新宣告出的 記憶體位置
		System.out.println(b01);
		System.out.println(b02);
		System.out.println(b03);
		System.out.println("=============");
		
		//印出新宣告出之後，並且呼叫class的方法
		System.out.println(b01.serialNo);
		System.out.println(b02.serialNo);
		System.out.println(b03.serialNo);
	}
}
