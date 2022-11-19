package com.example.demo_project.service.ifs;

import java.util.List;
import java.util.Set;

import com.example.demo_project.entity.Register;
import com.example.demo_project.vo.RegisterResp;

public interface RegisterService {

	// ���U
	public Register register(String account, String pwd, String name, int age, String city);

	// �E���b��
	public RegisterResp activeAccount(String account);

	// �s�W����(List��)
		public RegisterResp addRole(String account, List<String> roleList);
	
	// �s�W����(Set��)
	public RegisterResp addRole(String account, Set<String> roleSet);
}
