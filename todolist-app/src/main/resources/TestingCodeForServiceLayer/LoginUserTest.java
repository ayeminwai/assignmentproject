package com.bluestone.todolistapp.controller;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bluestone.todolistapp.model.LoginUserData;
import com.bluestone.todolistapp.model.ResponseData;
import com.bluestone.todolistapp.model.ResponseListData;
import com.bluestone.todolistapp.model.UserData;
import com.bluestone.todolistapp.serviceImpl.LoginService;
import com.bluestone.todolistapp.utility.EncryptionUtil;
import com.bluestone.todolistapp.utility.ServerUtil;
import com.bluestone.todolistapp.utility.StatusUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginUserTest {
	private UserData user;
	@Autowired
	LoginService service;

	@Autowired
	JdbcTemplate template;

	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Before
	public void setUp() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Content-Over", "L39YBf7QbFjl8cxh3UCRzbZCqAMiLclo+QcrFfZBsUA4ptw9QTv3z/mTAjGLvxb/");
		user = ServerUtil.getLoginUser(request);
		template.setDataSource(getDataSource());
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveUser() throws SQLException {

		LoginUserData data1 = new LoginUserData();
		data1.setId("0");
		data1.setCode("00001");
		data1.setName("User_001");
		data1.setEmail("ayeminwai001@gmail.com");
		data1.setAddress("Pathein , Shwethaungyan");
		data1.setPhone("+959972444001");
		data1.setPassword("ayeminwai_1");
		
		LoginUserData userData = save(data1).getResult(); 
		assertEquals(data1.getName(), readDataById(userData.getId()).getResult().getName());
		
		LoginUserData data2 = new LoginUserData();
		data2.setId("0");
		data2.setCode("00002");
		data2.setName("User_002");
		data2.setEmail("ayeminwai002@gmail.com");
		data2.setAddress("Pathein , Shwethaungyan");
		data2.setPhone("+959972444002");
		data2.setPassword("ayeminwai_2");
		
		userData = save(data2).getResult();
		assertEquals(data2.getName(), readDataById(userData.getId()).getResult().getName());
		
		LoginUserData data3 = new LoginUserData();
		data3.setId("0");
		data3.setCode("00003");
		data3.setName("User_003");
		data3.setEmail("ayeminwai003@gmail.com");
		data3.setAddress("Pathein , Shwethaungyan");
		data3.setPhone("+959972444003");
		data3.setPassword("ayeminwai_3");
		
		userData = save(data3).getResult();
		assertEquals(data3.getName(), readDataById(userData.getId()).getResult().getName());
	}
	
	@Test
	public void testUpdate() throws SQLException {
		LoginUserData data = readDataById("3").getResult();
		data.setName("Updated User");
		data.setPassword(EncryptionUtil.decrypt(data.getPassword()));
		
		
		data = update(data).getResult();
	}
	
	@Test
	public void testDelete() throws SQLException {
		assertEquals(StatusUtil.RepStatus.SUCCESS.name(), delete(readDataById("2").getResult().getId()).getMessage());
	}
	
	@Test
	public void testGetAllData() throws SQLException {
		List<LoginUserData> list = getAllData().getResult();
		for (LoginUserData data : list) {
			System.out.println(data.toString());
		}
	}
	
	private ResponseData<LoginUserData> save(LoginUserData data) {
		return service.save(data, user);
	}
	
	private ResponseData<LoginUserData> update(LoginUserData data) {
		return service.save(data, user);
	}
	
	private ResponseData<LoginUserData> delete(String id) {
		return service.delete(id, user);
	}
	
	private ResponseData<LoginUserData> readDataById(String id) {
		return service.readDataById(id, user);
	}
	
	private ResponseListData<LoginUserData> getAllData() {
		return service.getAllData(user);
	}


}
