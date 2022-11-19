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
	public RegisterResp register(@RequestBody RegisterReq req) {// 註冊帳號的方法

		// 新另設一個方法，去檢查輸入的內容是否為null，這邊是把方法再接回來
		RegisterResp checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult;
		}

		// 創建帳號
		Register reg = registerService.register(req.getAccount(), req.getPwd(), req.getName(), req.getAge(),
				req.getCity());

		// 不能註冊已經存在的帳號(Impl)
		if (reg == null) {
			return new RegisterResp(RegisterRtnCode.ACCOUNT_EXISTED.getMessage());
		}
		return new RegisterResp(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
	}

	private RegisterResp checkParam(RegisterReq req) {// 確認輸入內容是否為null!，方法一! //parameter(參數)
		// account, pwd, name cannot be null or empty
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterResp(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());// 利用列舉(enum)來回復訊息
		} else if (!StringUtils.hasText(req.getPwd())) {
			return new RegisterResp(RegisterRtnCode.PWD_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getName())) {
			return new RegisterResp(RegisterRtnCode.NAME_REQUIRED.getMessage());
		}
		return null;
	}

	@PostMapping(value = "/api/activeAccount")
	public RegisterResp activeAccount(@RequestBody RegisterReq req) {

		// 檢查輸入的內容是否為null
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterResp(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		return registerService.activeAccount(req.getAccount());

//		// 判斷激活是true還是false //已在Impl那邊利用RegisterResp回傳型態了所以不須寫下列程式!
//		boolean result = registerService.activeAccount(req.getAccount());
//		if (result) {
//			return new RegisterResp(null, RegisterRtnCode.SUCCESSFUL.getMessage());// true-->激活成功
//		}
//		return new RegisterResp(null, RegisterRtnCode.FAILURE.getMessage());// fail-->激活失敗
	}

	@PostMapping(value = "/api/addRoleList")
	public RegisterResp addRoleList(@RequestBody AddRoleListReq req) {
		// 檢查輸入的內容是否為null
		if (!StringUtils.hasText(req.getAccount())) {// 字串的判斷-->是null還是空
			return new RegisterResp(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
//		if (req.getRoleList().isEmpty()) {
		if (CollectionUtils.isEmpty(req.getRoleList())) {// 集合的判斷-->是null還是空
			return new RegisterResp(RegisterRtnCode.ROLE_LIST_IS_EMPTY.getMessage());
		}
		return registerService.addRole(req.getAccount(), req.getRoleList());
	}

	@PostMapping(value = "/api/addRoleSet")
	public RegisterResp addRoleSet(@RequestBody AddRoleSetReq req) {
		// 檢查輸入的內容是否為null
		if (!StringUtils.hasText(req.getAccount())) {// 字串的判斷-->是null還是空
			return new RegisterResp(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
//		if (req.getRoleSet() == null || req.getRoleSet().isEmpty()) {//要寫兩種才能判斷
		if (CollectionUtils.isEmpty(req.getRoleSet())) {// 集合的判斷-->是null還是空
			return new RegisterResp(RegisterRtnCode.ROLE_SET_IS_EMPTY.getMessage());
		}
		return registerService.addRole(req.getAccount(), req.getRoleSet());
	}

}
