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

import com.bluestone.todolistapp.model.ResponseData;
import com.bluestone.todolistapp.model.ResponseListData;
import com.bluestone.todolistapp.model.ToDoStepData;
import com.bluestone.todolistapp.model.UserData;
import com.bluestone.todolistapp.serviceImpl.ToDoStepService;
import com.bluestone.todolistapp.utility.ServerUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDoStepTest {
	private UserData user;
	@Autowired
	ToDoStepService service;

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

		ToDoStepData data1 = new ToDoStepData();
		data1.setId("0");
		data1.setName("New Requirement");
		data1.setUserId("1");
		ToDoStepData userData = save(data1).getResult(); 
		assertEquals(data1.getName(), readDataById(userData.getId()).getResult().getName());
		
		ToDoStepData data2 = new ToDoStepData();
		data2.setId("0");
		data2.setName("Coding");
		data2.setUserId("1");
		userData = save(data2).getResult();
		assertEquals(data2.getName(), readDataById(userData.getId()).getResult().getName());
		
		ToDoStepData data3 = new ToDoStepData();
		data3.setId("0");
		data3.setName("Unit Testing");
		data3.setUserId("1");
		userData = save(data3).getResult();
		assertEquals(data3.getName(), readDataById(userData.getId()).getResult().getName());
		
		ToDoStepData data4 = new ToDoStepData();
		data4.setId("0");
		data4.setName("UAT Testing");
		data4.setUserId("1");
		userData = save(data4).getResult();
		assertEquals(data4.getName(), readDataById(userData.getId()).getResult().getName());
		
		ToDoStepData data5 = new ToDoStepData();
		data5.setId("0");
		data5.setName("Finished");
		data5.setUserId("1");
		userData = save(data5).getResult();
		assertEquals(data5.getName(), readDataById(userData.getId()).getResult().getName());
		
		ToDoStepData data6 = new ToDoStepData();
		data6.setId("0");
		data6.setName("Get Sign Off");
		data6.setUserId("1");
		userData = save(data6).getResult();
		assertEquals(data6.getName(), readDataById(userData.getId()).getResult().getName());
	}
	
	@Test
	public void testUpdate() throws SQLException {
		ToDoStepData data = readDataById("2").getResult();
		data.setName("Development Time");
		data = update(data).getResult();
		System.out.println(data.toString());
	}
	
	@Test
	public void testDelete() throws SQLException {
		System.out.println(delete("4").getMessage());
	}
	
	@Test
	public void testGetAllData() throws SQLException {
		List<ToDoStepData> list = getAllData().getResult();
		for (ToDoStepData data : list) {
			System.out.println(data.toString());
		}
	}
	
	public ResponseData<ToDoStepData> save(ToDoStepData data){
		return service.save(data, user);
	}
	
	public ResponseData<ToDoStepData> update(ToDoStepData data){
		return service.save(data, user);
	}
	
	
	public ResponseData<ToDoStepData> delete(String id){
		return service.delete(id, user);
	}
	
	public ResponseListData<ToDoStepData> getAllData(){
		return service.getAllData(user);
	}
	
	public ResponseData<ToDoStepData> readDataById(String id){
		return service.readDataById(id, user);
	}
}
