package com.example.demo_project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo_project.entity.Register;
import com.example.demo_project.repository.RegisterDao;
import com.example.demo_project.service.ifs.RegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@SpringBootTest(classes = DemoProjectApplication.class) // �O�M�ש��UApplication���W�١A�åB�n�[.class
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // ���F�i�H�ϥ�@BeforeAll �M@AfterAll
public class RegisterTest {

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	@Autowired
	private RegisterService registerService;

	@Autowired
	private RegisterDao registerDao;

	// MockMvc �O��� WebApplicationContext �ҫإߪ�����A�i�Ψӽs�g web ���Ϊ���X����
	@Autowired
	private WebApplicationContext wac;

	// ��{�� http�ШD�������A�D�n�O�ΨӴ��� controller
	private MockMvc mockMvc;

	@BeforeAll // ��Ӵ��իe����@��
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@BeforeAll
	public void beforeAll() {
		System.out.println("Before All!!!");
	}
//	
//	@AfterAll
//	public void afterAll() {
//		System.out.println("After All!!!");
//	}
//
//	@BeforeEach
//	public void beforeEach() {
//		System.out.println("Before Each!!!");
//	}

	@SuppressWarnings("unchecked")
	@Test
	public void registerControllerTest() throws Exception {// controller������
		// �p�G header ���ݭn�[��L�ѼƮ�(�h��)�A�i�Φ��覡
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// set request_body
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("account", "A96");
		map.put("pwd", "A12345");
		map.put("name", "David");
		map.put("age", 22);
		map.put("city", "Taipei");

		// map to string //�Nmap���F���ন�r��
		ObjectMapper objectMapper = new ObjectMapper();
		String mapString = objectMapper.writeValueAsString(map);

//		System.out.println(map.toString());
		// {account=A96, pwd=A12345, name=David, age=22, city=Taipei}
		System.out.println(mapString);
		// ����postman requestbody�榡
		// {"account":"A96","pwd":"A12345","name":"David", "age":22, "city":"Taipei"}

		// ����Postman�̪� Http Method)
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/register") // ��mockMvc ���� http
				.contentType(CONTENT_TYPE) // Headers �n�[���ѼƴN�u�� content_type�ɡA�i�����ϥΡA�����z�L Headers�[�J
//				.headers(headers) //headers �� contentType �u��}�@��
				.content(mapString)); // �̭��O��body���F��A���O�]���٨S�ةҥH�N����Ŧr��//Mock�̭��u���\��r��A�ҥH�U���i���૬

		// get response && �N response�����e�ন�r��
//		MockHttpServletResponse httpResponse = result.andReturn().getResponse();//-->�Ĥ@����k
//		String resString = httpResponse.getContentAsString();
		String resString = result.andReturn().getResponse().getContentAsString();// -->�ĤG����k

		// �N�o�쪺���G--> response�r�� �A�ন Json(map)
		JacksonJsonParser jsonParser = new JacksonJsonParser();
//		Map<String, Object> resData = jsonParser.parseMap(resString);
//		String rtnmessage = (String) resData.get("message");
//		System.out.println(resData);
		Map<String, Object> resData = jsonParser.parseMap(resString);
		Object rtnmessage = resData.get("message");
		System.out.println(rtnmessage);
		Assert.isTrue(rtnmessage.equals("Success!"), "Message error!");
		System.out.println(resString);

		// (Register):�૬�A�Nmap����Object���૬���ݭn��Register���A
		Map<String, Object> rtnInfo = (Map<String, Object>) resData.get("register_info");
		Assert.isTrue(((String) rtnInfo.get("account")).equals("A96"), "account error"); // �qmap��account(key��)�������value��
		System.out.println(rtnInfo);

		System.out.println(resData);

	}

//	@Test
//	public void registerTest() {
//		Register reg = registerService.register("A99", "12345678", "Alice", 18, "Tainan");
//		Assert.notNull(reg, "Result is null!");// Asset.isTrue�N�O�P�_�O�_���u�A�P�_�Ĥ@�ӰѼƬO�_���u�A�Y���_�h��ܫ᭱���T��
//		Assert.isTrue(reg.getAccount().equalsIgnoreCase("A99"), "Account error!");
//
//		registerDao.delete(reg);
//
//		Assert.isTrue(!registerDao.findById("A99").isPresent(), "reg is not null!!");// 2�ܤ@(1)
//
//		Assert.isTrue(!registerDao.existsById("A99"), "reg is not null!!");// 2�ܤ@(2)
//
//		System.out.println("Register test!!!");
//	}

//	@Test
//	public void activeAccountTest() {
//		// register new account
//		Register reg = registerService.register("A99", "12345678", "Alice", 18, "Tainan");
//		Assert.isTrue(!reg.isActive(), "Account is active!");// reg.isActive() == false,-->!reg.isActive()�A�u�n�OisTrue�N���U��
//		// active registerd account
//		registerService.activeAccount("A99");
//		Register newreg = registerDao.findById("A99").get();
//		Assert.isTrue(newreg.isActive(), "Account is inactive!");// reg.isActive() == true,newreg.isActive()
//		registerDao.delete(newreg);// �N����ƧR��
//		System.out.println("Register test!!!");
//
//	}

	@Test
	public void addRoleTest() {// (LIST��)
		List<String> roleList = new ArrayList<>();
		roleList.add("SA");
		roleList.add("SD");
		roleList.add("SA");
		roleList.add("SD");
		roleList.add("PM");
		registerService.addRole("A03", roleList);
		System.out.println("Register test!!!");
	}
//		String str = "SD,  SA";
//		String[] strarray = str.split(",");
//		for(String item : strarray) {
//			System.out.println(item);
//			System.out.println(item.length());
//			System.out.println(item.trim());
//			System.out.println(item.trim().length());
//			System.out.println("========");
//		}

	@SuppressWarnings("unchecked")
	// 1103
	@Test
	public void addRoleListControllerTest() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> map = new LinkedHashMap<>();
		List<String> roleList = new ArrayList<>();
		roleList.add("11");
		roleList.add("22");
		map.put("account", "A96");
		map.put("role_list", roleList);

		ObjectMapper objectMapper = new ObjectMapper();
		String mapString = objectMapper.writeValueAsString(map);

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/addRoleList").contentType(CONTENT_TYPE)
//						.headers(headers) //
				.content(mapString));

		String resString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		Map<String, Object> resData = jsonParser.parseMap(resString);
		Object rtnmessage = (String) resData.get("message");
		System.out.println(rtnmessage);
		Assert.isTrue(rtnmessage.equals("Success!"), "Message error!");

		Map<String, Object> rtnInfo = (Map<String, Object>) resData.get("register_info");
		Assert.isTrue(((String) rtnInfo.get("account")).equals("A96"), "account error");
		System.out.println(rtnInfo);

		System.out.println(resData);
	}

	@Test
	public void updateRegisterInfoDaoTest() {
		int result = registerDao.updateRegisterInfo("David", 22, "�x�_", new Date(), "�b��1");
		System.out.println("------->>" + result);
	}

	@Test
	public void doQueryTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		List<Register> result = registerDao.doQueryByExpiredRegTime(date);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}

	@Test
	public void doQueryWithPageSizeTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		List<Register> result = registerDao.doQueryByExpiredRegTime(date, 2);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}

	@Test
	public void doQueryWithStartPositionTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		List<Register> result = registerDao.doQueryByExpiredRegTime(date, -1, 2);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}
	
	@Test
	public void doNativeQueryTest() throws ParseException {
		String dateStr = "2022-11-10";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		List<Register> result = registerDao.doNativeQueryByExpiredRegTime(date, -1, 2);
		System.out.println(result.size());
		for (Register item : result) {
			System.out.println(item.getAccount());
		}
	}

}