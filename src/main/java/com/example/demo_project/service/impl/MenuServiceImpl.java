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
			res.setMessage("�\�I�~����J���~!");
			return res;
		}
		if (menuOp.isPresent()) {
			res.setMessage("�w���o�ӫ~��!");
			return res;
		}
		if (price <= 0) {
			res.setMessage("�Ыث~�������B���i��0!");
			return res;
		}
		Menu menu = new Menu();
		menu.setName(name);
		menu.setPrice(price);

		res.setMenu(menu);
		res.setMessage("�Ыئ��\!");
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
			resp.setMessage("���~��!");
			return resp;
		} else {
			resp.setMessage("�d�L���~��!");
			return resp;
		}
	}

	@Override
	public MenuResp OrderMenu(List<MenuReq> orderlist) {
		int total = 0;
		MenuResp resp = new MenuResp();
		Map<String, Integer> orderMap = new HashMap<>(); // �\�I�W�B����
		List<String> messagelist = new ArrayList<>(); // �\�I����
		for (MenuReq orderItem : orderlist) {
			if (StringUtils.hasText(orderItem.getName())) {// StringUtils.hasText():�p�G�r���ǦC���� null ��,�}�B�r���ǦC�����פj�_ 0
															// ,�}�B���t���ťզr���ǦC,�h�Ǧ^ true
				Optional<Menu> menuOp = menuDao.findById(orderItem.getName());
				if (menuOp.isPresent()) {// ���b(���Ƥ���0)
					if (orderItem.getQuantity() <= 0) {
						orderItem.setQuantity(0);
					}
					orderMap.put(orderItem.getName(), orderItem.getQuantity());
					messagelist.add(orderItem.getName() + ":" + orderItem.getQuantity() + "���A�@"
							+ (orderItem.getQuantity() * menuOp.get().getPrice()) + "��");
					total += orderItem.getQuantity() * menuOp.get().getPrice();
				} else {
					orderMap.put(orderItem.getName(), 0);
					messagelist.add(orderItem.getName() + ":" + "0���A�@0��");
				}
			}
			resp.setOrderMap(orderMap);
		}
		if (total >= 500) {
			int price = (int) (total * 0.9);
			messagelist.add("���B�j��500���A��9��:" + price + "��");
		} else {
			messagelist.add("����500���A�`���B�F" + total + "��");
		}
		resp.setMessagelist(messagelist);
		return resp;
	}

}
