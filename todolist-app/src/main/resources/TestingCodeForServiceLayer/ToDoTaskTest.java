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
import com.bluestone.todolistapp.model.ToDoTaskData;
import com.bluestone.todolistapp.model.UserData;
import com.bluestone.todolistapp.serviceImpl.ToDoTaskService;
import com.bluestone.todolistapp.utility.ServerUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDoTaskTest {
	private UserData user;
	@Autowired
	ToDoTaskService service;

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

		ToDoTaskData data1 = new ToDoTaskData();
		data1.setName("Task 1");
		data1.setStepId("1");
		
		ToDoTaskData todoTask = save(data1).getResult(); 
		assertEquals(data1.getName(), readDataById(todoTask.getId()).getResult().getName());
		
		ToDoTaskData data2 = new ToDoTaskData();
		data2.setName("Task 2");
		data2.setStepId("2");
		todoTask = save(data2).getResult();
		assertEquals(data2.getName(), readDataById(todoTask.getId()).getResult().getName());
		
		ToDoTaskData data3 = new ToDoTaskData();
		data3.setName("Task 3");
		data3.setStepId("1");
		
		todoTask = save(data3).getResult();
		assertEquals(data3.getName(), readDataById(todoTask.getId()).getResult().getName());
		
		ToDoTaskData data4 = new ToDoTaskData();
		data4.setName("Task 4");
		data4.setStepId("3");
		
		todoTask = save(data4).getResult();
		assertEquals(data4.getName(), readDataById(todoTask.getId()).getResult().getName());
	}
	
	@Test
	public void testUpdate()  throws SQLException {
		ToDoTaskData data = readDataById("2").getResult();
		data.setName("Development Time");
		data = update(data).getResult();
		System.out.println(data.toString());
	}
	
	@Test
	public void testMoveStep()  throws SQLException {
		ToDoTaskData data = readDataById("1").getResult();
		data.setStepId("6");
		System.out.print("BEFORE MOVE : STEP -> " + data.getStepId());
		data = moveStep(data).getResult();
		System.out.print("AFTER MOVE : STEP -> " + data.getStepId());
	}
	
	@Test
	public void testDelete()  throws SQLException {
		System.out.println(delete("4").getMessage());
	}
	
	@Test
	public void testGetAllData()  throws SQLException {
		List<ToDoTaskData> list = getAllData().getResult();
		for (ToDoTaskData data : list) {
			System.out.println(data.toString());
		}
	}
	
	private ResponseData<ToDoTaskData> save(ToDoTaskData data) {
		return service.save(data, user);
	}
	
	private ResponseData<ToDoTaskData> update(ToDoTaskData data) {
		return service.save(data, user);
	}
	
	private ResponseData<ToDoTaskData> delete(String id) {
		return service.delete(id, user);
	}
	
	private ResponseData<ToDoTaskData> readDataById(String id) {
		return service.readDataById(id, user);
	}
	
	private ResponseListData<ToDoTaskData> getAllData() {
		return service.getAllData(user);
	}
	
	private ResponseData<ToDoTaskData> moveStep(ToDoTaskData data){
		return service.moveStep(data, user);
	}
}
