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
		// ������U�w�s�b���b��
		if (registerDao.existsById(account)) {
			return null;
		}

		// �Ыطs�b��
		Register reg = new Register(account, pwd, name, age, city);
		reg.setRegTime(new Date());// new Date()-->��e�ɶ�
//		reg.setActive(false);	//�w�]boolean�Ofalse�A�ҥH�o�@�q�i�H���ΦA�g�X��!
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
			return new RegisterResp(null, RegisterRtnCode.SUCCESSFUL.getMessage());// �P�_�E���Otrue�٬Ofalse-->SUCCESSFUL
		}
		return new RegisterResp(null, RegisterRtnCode.FAILURE.getMessage());// �P�_�E���Otrue�٬Ofalse-->FAILURE
	}

	@Override // �s�W����(List��)
	public RegisterResp addRole(String account, List<String> roleList) {
		Optional<Register> regOp = registerDao.findById(account);

		// �b���s�b����
		if (regOp.isPresent()) {

			// �h���Ѽ�(�ǥ�)�̪�����
			Set<String> roleSet = new HashSet<>();// ��Set���^�ӡA�N���|�����ƪ������F�A�]��set�u��O�ߤ@������
			for (String str : roleList) {
				roleSet.add(str);
			}

			// �h��DB���w�s�b���ȩM�Ѽƪ��ȡA��̭��ƪ�����
			Register reg = regOp.get();
			String role = reg.getRole();// �i�঳�h�ӡA�γr��(,)�Ϲj�A�Ҧp: General, SA, PM
			String[] roleArray = role.split(",");// Split�Ω�N�r����ΡA�æ^�Ǧ��@�Ӧr�ꪺ�}�C(�åB�̭��ťշ|�O�d)
			for (String item : roleArray) {
				String str = item.trim();// �N�C�ӵ��G����trim�h���ťաA�A�@�֦^��
				roleSet.add(str);
			}

			// ��String�h���A�|�]�t���A�� // �ҥH�n�N�e�᪺���A���Φ�m(.substring())�h���R��
			String newstr = roleSet.toString().substring(1, roleSet.toString().length() - 1);
			reg.setRole(newstr);// �N���X��^�r��A�åB�w�g�h���e�ᤤ�A������A�Aset�^�h
			registerDao.save(reg);
			return new RegisterResp(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		return new RegisterResp(null, RegisterRtnCode.ADD_ROLE_FAILIRE.getMessage());
	}

	@Override // �s�W����(Set��)
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
