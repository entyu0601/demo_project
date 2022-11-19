package com.example.demo_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.service.ifs.MenuService;
import com.example.demo_project.vo.MenuReq;
import com.example.demo_project.vo.MenuResp;

@RestController // �Ω�^�� JSON�BXML����ơA������^�� HTML ����
public class MenuController { // Controller-->API ���J�f�I�A�z�L URI �өw��

	@Autowired
	private MenuService menuService;

	@PostMapping(value = "/api/addMenuItem")
	public MenuResp addMenuItem(@RequestBody MenuReq request) {// @RequestBody--> �~��JSON���F��Mapping�ݩʨ�o��class�̭�
		return menuService.addMenuItem(request.getName(), request.getPrice());
	}

	@PostMapping(value = "/api/getAllMenus") // �B�z��}��M�A�i�Ω��khttp��Method(post/get..��)�C
	public MenuResp getAllMenus() {
		return new MenuResp(menuService.getAllMenus());
	}

	@PostMapping(value = "/api/getOnlyMenu")
	public MenuResp getOnlyMenu(@RequestBody MenuReq request) {
		return menuService.getOnlyMenu(request.getName());
	}

	@PostMapping(value = "/api/OrderMenu")
	public MenuResp OrderMenu(@RequestBody List<MenuReq> orderlist) {// ��� HTTP �T���O JSON �άO XML �榡�A���N����Ƭ����w�����Ѽ�
		return menuService.OrderMenu(orderlist);
	}

}
