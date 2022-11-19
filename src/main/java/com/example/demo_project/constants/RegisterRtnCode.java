package com.example.demo_project.constants;

public enum RegisterRtnCode {// enum-->�C�|

	SUCCESSFUL("200", "Success!"),
	FAILURE("500", "Account active is fail!"),
	ACCOUNT_REQUIRED("400", "Account is required!"),
	PWD_REQUIRED("400", "Pwd is required!"), 
	NAME_REQUIRED("400", "Name is required!"),
	ACCOUNT_EXISTED("403", "Account is already exist!"),
	ADD_ROLE_FAILIRE("400","Add role fail!"),
	ROLE_LIST_IS_EMPTY("400","Role list is empty!"),
	ROLE_SET_IS_EMPTY("400","Role set is empty!");;;

	private String code;

	private String message;

	// �benum���غc��k�u�ରprivate
	private RegisterRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	// �q�`enum�o��u�n��get�N�n�Aset�Τ���
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
