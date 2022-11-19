package com.example.demo_project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo_project.entity.Menu;
import com.example.demo_project.repository.MenuDao;
import com.example.demo_project.service.ifs.MenuService;
import com.example.demo_project.vo.MenuReq;
import com.example.demo_project.vo.MenuResp;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public MenuResp addMenuItem(String name, Integer price) {
		MenuResp res = new MenuResp();
		Optional<Menu> menuOp = menuDao.findById(name);

		if (!StringUtils.hasText(name)) {
			res.setMessage("餐點品項輸入錯誤!");
			return res;
		}
		if (menuOp.isPresent()) {
			res.setMessage("已有這個品項!");
			return res;
		}
		if (price <= 0) {
			res.setMessage("創建品項之金額不可為0!");
			return res;
		}
		Menu menu = new Menu();
		menu.setName(name);
		menu.setPrice(price);

		res.setMenu(menu);
		res.setMessage("創建成功!");
		menuDao.save(menu);
		return res;

	}

	@Override
	public List<Menu> getAllMenus() {
		List<Menu> getAllMenus = menuDao.findAll();
		return getAllMenus;
	}

	@Override
	public MenuResp getOnlyMenu(String name) {
		MenuResp resp = new MenuResp();
		Optional<Menu> menuOp = menuDao.findById(name);
		if (menuOp.isPresent()) {
			Menu menu = menuOp.get();
			resp.setMenu(menu);
			resp.setMessage("找到品項!");
			return resp;
		} else {
			resp.setMessage("查無此品項!");
			return resp;
		}
	}

	@Override
	public MenuResp OrderMenu(List<MenuReq> orderlist) {
		int total = 0;
		MenuResp resp = new MenuResp();
		Map<String, Integer> orderMap = new HashMap<>(); // 餐點名、份數
		List<String> messagelist = new ArrayList<>(); // 餐點明細
		for (MenuReq orderItem : orderlist) {
			if (StringUtils.hasText(orderItem.getName())) {// StringUtils.hasText():如果字元序列不為 null 值,并且字元序列的長度大于 0
															// ,并且不含有空白字元序列,則傳回 true
				Optional<Menu> menuOp = menuDao.findById(orderItem.getName());
				if (menuOp.isPresent()) {// 防呆(份數不為0)
					if (orderItem.getQuantity() <= 0) {
						orderItem.setQuantity(0);
					}
					orderMap.put(orderItem.getName(), orderItem.getQuantity());
					messagelist.add(orderItem.getName() + ":" + orderItem.getQuantity() + "份，共"
							+ (orderItem.getQuantity() * menuOp.get().getPrice()) + "元");
					total += orderItem.getQuantity() * menuOp.get().getPrice();
				} else {
					orderMap.put(orderItem.getName(), 0);
					messagelist.add(orderItem.getName() + ":" + "0份，共0元");
				}
			}
			resp.setOrderMap(orderMap);
		}
		if (total >= 500) {
			int price = (int) (total * 0.9);
			messagelist.add("金額大於500元，打9折:" + price + "元");
		} else {
			messagelist.add("未滿500元，總金額；" + total + "元");
		}
		resp.setMessagelist(messagelist);
		return resp;
	}

}
