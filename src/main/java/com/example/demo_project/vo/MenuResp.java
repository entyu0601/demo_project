package com.example.demo_project.vo;

import java.util.List;
import java.util.Map;

import com.example.demo_project.entity.Menu;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // 避免JSON印出null(實體類與JSON互轉的時候屬性值為null的不參與序列化)
public class MenuResp {

	private Menu menu;

	private String message;

	private List<Menu> menuList;

	private Map<String, Integer> orderMap;

	private List<String> messagelist;

	public MenuResp() {

	}

	public MenuResp(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Integer> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<String, Integer> orderMap) {
		this.orderMap = orderMap;
	}

	public List<String> getMessagelist() {
		return messagelist;
	}

	public void setMessagelist(List<String> messagelist) {
		this.messagelist = messagelist;
	}

}
