package com.example.demo_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.constants.RegisterRtnCode;
import com.example.demo_project.entity.Register;
import com.example.demo_project.service.ifs.RegisterService;
import com.example.demo_project.vo.AddRoleListReq;
import com.example.demo_project.vo.AddRoleSetReq;
import com.example.demo_project.vo.RegisterReq;
import com.example.demo_project.vo.RegisterResp;

@RestController
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@PostMapping(value = "/api/register")
	public RegisterResp register(@RequestBody RegisterReq req) {// ���U�b������k

		// �s�t�]�@�Ӥ�k�A�h�ˬd��J�����e�O�_��null�A�o��O���k�A���^��
		RegisterResp checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult;
		}

		// �Ыرb��
		Register reg = registerService.register(req.getAccount(), req.getPwd(), req.getName(), req.getAge(),
				req.getCity());

		// ������U�w�g�s�b���b��(Impl)
		if (reg == null) {
			return new RegisterResp(RegisterRtnCode.ACCOUNT_EXISTED.getMessage());
		}
		return new RegisterResp(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
	}

	private RegisterResp checkParam(RegisterReq req) {// �T�{��J���e�O�_��null!�A��k�@! //parameter(�Ѽ�)
		// account, pwd, name cannot be null or empty
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterResp(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());// �Q�ΦC�|(enum)�Ӧ^�_�T��
		} else if (!StringUtils.hasText(req.getPwd())) {
			return new RegisterResp(RegisterRtnCode.PWD_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getName())) {
			return new RegisterResp(RegisterRtnCode.NAME_REQUIRED.getMessage());
		}
		return null;
	}

	@PostMapping(value = "/api/activeAccount")
	public RegisterResp activeAccount(@RequestBody RegisterReq req) {

		// �ˬd��J�����e�O�_��null
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterResp(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		return registerService.activeAccount(req.getAccount());

//		// �P�_�E���Otrue�٬Ofalse //�w�bImpl����Q��RegisterResp�^�ǫ��A�F�ҥH�����g�U�C�{��!
//		boolean result = registerService.activeAccount(req.getAccount());
//		if (result) {
//			return new RegisterResp(null, RegisterRtnCode.SUCCESSFUL.getMessage());// true-->�E�����\
//		}
//		return new RegisterResp(null, RegisterRtnCode.FAILURE.getMessage());// fail-->�E������
	}

	@PostMapping(value = "/api/addRoleList")
	public RegisterResp addRoleList(@RequestBody AddRoleListReq req) {
		// �ˬd��J�����e�O�_��null
		if (!StringUtils.hasText(req.getAccount())) {// �r�ꪺ�P�_-->�Onull�٬O��
			return new RegisterResp(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
//		if (req.getRoleList().isEmpty()) {
		if (CollectionUtils.isEmpty(req.getRoleList())) {// ���X���P�_-->�Onull�٬O��
			return new RegisterResp(RegisterRtnCode.ROLE_LIST_IS_EMPTY.getMessage());
		}
		return registerService.addRole(req.getAccount(), req.getRoleList());
	}

	@PostMapping(value = "/api/addRoleSet")
	public RegisterResp addRoleSet(@RequestBody AddRoleSetReq req) {
		// �ˬd��J�����e�O�_��null
		if (!StringUtils.hasText(req.getAccount())) {// �r�ꪺ�P�_-->�Onull�٬O��
			return new RegisterResp(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
//		if (req.getRoleSet() == null || req.getRoleSet().isEmpty()) {//�n�g��ؤ~��P�_
		if (CollectionUtils.isEmpty(req.getRoleSet())) {// ���X���P�_-->�Onull�٬O��
			return new RegisterResp(RegisterRtnCode.ROLE_SET_IS_EMPTY.getMessage());
		}
		return registerService.addRole(req.getAccount(), req.getRoleSet());
	}

}
