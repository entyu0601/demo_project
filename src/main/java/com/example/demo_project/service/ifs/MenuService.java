package com.example.demo_project.service.ifs;

import java.util.List;

import com.example.demo_project.entity.Menu;
import com.example.demo_project.vo.MenuReq;
import com.example.demo_project.vo.MenuResp;

public interface MenuService {

	public MenuResp addMenuItem(String name, Integer price);

	public List<Menu> getAllMenus();

	public MenuResp getOnlyMenu(String name);

	public MenuResp OrderMenu(List<MenuReq> orderlist);

}
