package com.example.demo_project.service.ifs;

import java.util.List;
import java.util.Set;

import com.example.demo_project.entity.Register;
import com.example.demo_project.vo.RegisterResp;

public interface RegisterService {

	// 註冊
	public Register register(String account, String pwd, String name, int age, String city);

	// 激活帳號
	public RegisterResp activeAccount(String account);

	// 新增角色(List版)
		public RegisterResp addRole(String account, List<String> roleList);
	
	// 新增角色(Set版)
	public RegisterResp addRole(String account, Set<String> roleSet);
}
