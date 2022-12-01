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
@SpringBootTest(classes = DemoProjectApplication.class) // 是專案底下Application的名稱，並且要加.class
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 為了可以使用@BeforeAll 和@AfterAll
public class RegisterTest {

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	@Autowired
	private RegisterService registerService;

	@Autowired
	private RegisterDao registerDao;

	// MockMvc 是基於 WebApplicationContext 所建立的物件，可用來編寫 web 應用的整合測試
	@Autowired
	private WebApplicationContext wac;

	// 實現對 http請求的模擬，主要是用來測試 controller
	private MockMvc mockMvc;

	@BeforeAll // 整個測試前執行一次
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
	public void registerControllerTest() throws Exception {// controller的測試
		// 如果 header 有需要加其他參數時(多個)，可用此方式
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// set request_body
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("account", "A96");
		map.put("pwd", "A12345");
		map.put("name", "David");
		map.put("age", 22);
		map.put("city", "Taipei");

		// map to string //將map的東西轉成字串
		ObjectMapper objectMapper = new ObjectMapper();
		String mapString = objectMapper.writeValueAsString(map);

//		System.out.println(map.toString());
		// {account=A96, pwd=A12345, name=David, age=22, city=Taipei}
		System.out.println(mapString);
		// 此為postman requestbody格式
		// {"account":"A96","pwd":"A12345","name":"David", "age":22, "city":"Taipei"}

		// 模擬Postman裡的 Http Method)
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/register") // 用mockMvc 模擬 http
				.contentType(CONTENT_TYPE) // Headers 要加的參數就只有 content_type時，可直接使用，不須透過 Headers加入
//				.headers(headers) //headers 跟 contentType 只能開一個
				.content(mapString)); // 裡面是放body的東西，但是因為還沒建所以就先放空字串//Mock裡面只允許放字串，所以下面進行轉型

		// get response && 將 response的內容轉成字串
//		MockHttpServletResponse httpResponse = result.andReturn().getResponse();//-->第一種轉法
//		String resString = httpResponse.getContentAsString();
		String resString = result.andReturn().getResponse().getContentAsString();// -->第二種轉法

		// 將得到的結果--> response字串 再轉成 Json(map)
		JacksonJsonParser jsonParser = new JacksonJsonParser();
//		Map<String, Object> resData = jsonParser.parseMap(resString);
//		String rtnmessage = (String) resData.get("message");
//		System.out.println(resData);
		Map<String, Object> resData = jsonParser.parseMap(resString);
		Object rtnmessage = resData.get("message");
		System.out.println(rtnmessage);
		Assert.isTrue(rtnmessage.equals("Success!"), "Message error!");
		System.out.println(resString);

		// (Register):轉型，將map中的Object值轉型成需要的Register型態
		Map<String, Object> rtnInfo = (Map<String, Object>) resData.get("register_info");
		Assert.isTrue(((String) rtnInfo.get("account")).equals("A96"), "account error"); // 從map的account(key值)找對應的value值
		System.out.println(rtnInfo);

		System.out.println(resData);

	}

//	@Test
//	public void registerTest() {
//		Register reg = registerService.register("A99", "12345678", "Alice", 18, "Tainan");
//		Assert.notNull(reg, "Result is null!");// Asset.isTrue就是判斷是否為真，判斷第一個參數是否為真，若為否則顯示後面的訊息
//		Assert.isTrue(reg.getAccount().equalsIgnoreCase("A99"), "Account error!");
//
//		registerDao.delete(reg);
//
//		Assert.isTrue(!registerDao.findById("A99").isPresent(), "reg is not null!!");// 2擇一(1)
//
//		Assert.isTrue(!registerDao.existsById("A99"), "reg is not null!!");// 2擇一(2)
//
//		System.out.println("Register test!!!");
//	}

//	@Test
//	public void activeAccountTest() {
//		// register new account
//		Register reg = registerService.register("A99", "12345678", "Alice", 18, "Tainan");
//		Assert.isTrue(!reg.isActive(), "Account is active!");// reg.isActive() == false,-->!reg.isActive()，只要是isTrue就往下走
//		// active registerd account
//		registerService.activeAccount("A99");
//		Register newreg = registerDao.findById("A99").get();
//		Assert.isTrue(newreg.isActive(), "Account is inactive!");// reg.isActive() == true,newreg.isActive()
//		registerDao.delete(newreg);// 將假資料刪掉
//		System.out.println("Register test!!!");
//
//	}

	@Test
	public void addRoleTest() {// (LIST版)
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
		int result = registerDao.updateRegisterInfo("David", 22, "台北", new Date(), "帳號1");
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