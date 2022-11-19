package com.example.demo_project.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_project.constants.RegisterRtnCode;
import com.example.demo_project.entity.Register;
import com.example.demo_project.repository.RegisterDao;
import com.example.demo_project.service.ifs.RegisterService;
import com.example.demo_project.vo.RegisterResp;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDao registerDao;

	@Override
	public Register register(String account, String pwd, String name, int age, String city) {
		// 不能註冊已存在的帳號
		if (registerDao.existsById(account)) {
			return null;
		}

		// 創建新帳號
		Register reg = new Register(account, pwd, name, age, city);
		reg.setRegTime(new Date());// new Date()-->當前時間
//		reg.setActive(false);	//預設boolean是false，所以這一段可以不用再寫出來!
		reg.setRole("General");
		return registerDao.save(reg);
	}

	@Override
	public RegisterResp activeAccount(String account) {
		Optional<Register> regOp = registerDao.findById(account);
		if (regOp.isPresent()) {
			Register reg = regOp.get();
			reg.setActive(true);
			registerDao.save(reg);
			return new RegisterResp(null, RegisterRtnCode.SUCCESSFUL.getMessage());// 判斷激活是true還是false-->SUCCESSFUL
		}
		return new RegisterResp(null, RegisterRtnCode.FAILURE.getMessage());// 判斷激活是true還是false-->FAILURE
	}

	@Override // 新增角色(List版)
	public RegisterResp addRole(String account, List<String> roleList) {
		Optional<Register> regOp = registerDao.findById(account);

		// 帳號存在的話
		if (regOp.isPresent()) {

			// 去除參數(學生)裡的重複
			Set<String> roleSet = new HashSet<>();// 用Set接回來，就不會有重複的元素了，因為set只能是唯一的元素
			for (String str : roleList) {
				roleSet.add(str);
			}

			// 去除DB中已存在的值和參數的值，兩者重複的部分
			Register reg = regOp.get();
			String role = reg.getRole();// 可能有多個，用逗號(,)區隔，例如: General, SA, PM
			String[] roleArray = role.split(",");// Split用於將字串切割，並回傳成一個字串的陣列(並且裡面空白會保留)
			for (String item : roleArray) {
				String str = item.trim();// 將每個結果都用trim去掉空白，再一併回傳
				roleSet.add(str);
			}

			// 用String去接，會包含中括號 // 所以要將前後的中括號用位置(.substring())去做刪掉
			String newstr = roleSet.toString().substring(1, roleSet.toString().length() - 1);
			reg.setRole(newstr);// 將集合轉回字串，並且已經去掉前後中括號之後，再set回去
			registerDao.save(reg);
			return new RegisterResp(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		return new RegisterResp(null, RegisterRtnCode.ADD_ROLE_FAILIRE.getMessage());
	}

	@Override // 新增角色(Set版)
	public RegisterResp addRole(String account, Set<String> roleSet) {
		Optional<Register> regOp = registerDao.findById(account);
		if (regOp.isPresent()) {
			Register reg = regOp.get();
			String role = reg.getRole();
			String[] roleArray = role.split(",");
			for (String item : roleArray) {
				String str = item.trim();
				roleSet.add(str);
			}
			String newstr = roleSet.toString().substring(1, roleSet.toString().length() - 1);
			reg.setRole(newstr);
			registerDao.save(reg);
			return new RegisterResp(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		return new RegisterResp(null, RegisterRtnCode.ADD_ROLE_FAILIRE.getMessage());
	}

}
