package com.example.demo_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.service.ifs.MenuService;
import com.example.demo_project.vo.MenuReq;
import com.example.demo_project.vo.MenuResp;

@RestController // 用於回傳 JSON、XML等資料，但不能回傳 HTML 頁面
public class MenuController { // Controller-->API 的入口點，透過 URI 來定位

	@Autowired
	private MenuService menuService;

	@PostMapping(value = "/api/addMenuItem")
	public MenuResp addMenuItem(@RequestBody MenuReq request) {// @RequestBody--> 外部JSON的東西Mapping屬性到這個class裡面
		return menuService.addMenuItem(request.getName(), request.getPrice());
	}

	@PostMapping(value = "/api/getAllMenus") // 處理位址對映，可用於方法http的Method(post/get..等)。
	public MenuResp getAllMenus() {
		return new MenuResp(menuService.getAllMenus());
	}

	@PostMapping(value = "/api/getOnlyMenu")
	public MenuResp getOnlyMenu(@RequestBody MenuReq request) {
		return menuService.getOnlyMenu(request.getName());
	}

	@PostMapping(value = "/api/OrderMenu")
	public MenuResp OrderMenu(@RequestBody List<MenuReq> orderlist) {// 表示 HTTP 訊息是 JSON 或是 XML 格式，須將其轉化為指定類型參數
		return menuService.OrderMenu(orderlist);
	}

}
