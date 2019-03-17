package com.bluestone.todolistapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bluestone.todolistapp.TodolistAppApplicationTests;
import com.bluestone.todolistapp.utility.ServerUtil;
/*
	NEED TO RUN CREATE CLEAN QUERY BEFORE RUN THE TEST
	
	DIRECTORY -> RESOURCES FOLDER -> QUERY FOLDER ->  QUERY_FOR_NEW_DB_WITHOUT_RELATIONSHIP.TXT
*/
public class TestWebApp extends TodolistAppApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		ServerUtil.IS_DEV = true;
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	//Login User Controller
	@Test
	public void testLoginSave() throws Exception {
		mockMvc.perform(post("http://localhost:8085/bluestone/main/save").contentType("application/json;charset=UTF-8").content("{\"code\":\"0001\",\"name\":\"Aye Min Wai\",\"password\":\"amw\",\"email\":\"ayeminwai@gmail.com\",\"address\":\"Pathein\",\"phone\":\"+959972784660\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testLoginUpdate() throws Exception {
		mockMvc.perform(post("http://localhost:8085/bluestone/main/save").contentType("application/json;charset=UTF-8").content("{\"id\":\"1\",\"code\":\"0001\",\"name\":\"Myat Min Soe\",\"password\":\"amw\",\"email\":\"ayeminwai@gmail.com\",\"address\":\"Pathein\",\"phone\":\"+959972784660\"}"))
				.andExpect(status().isOk())		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testLoginReadDataById() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/main/readDataById/1").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testLoginGetAllData() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/main/getAllData").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(post("http://localhost:8085/bluestone/main/login").contentType("application/json;charset=UTF-8").content("{\"email\":\"ayeminwai1@gmail.com\",\"password\":\"amw\"}"))
				.andExpect(status().isOk())		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testLoginDelete() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/main/delete/5").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	// ToDoStep Controller
	
	@Test
	public void testToDoStepSave() throws Exception {
		mockMvc.perform(post("http://localhost:8085/bluestone/todostep/save").contentType("application/json;charset=UTF-8").content("{\"name\":\"Step20\",\"userId\":\"1\"}"))
				.andExpect(status().isOk())		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoStepUpdate() throws Exception {
		mockMvc.perform(post("http://localhost:8085/bluestone/todostep/save").contentType("application/json;charset=UTF-8").content("{\"id\":\"1\",\"email\":\"ayeminwai88@gmail.com\",\"password\":\"amw\"}"))
				.andExpect(status().isOk())		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoStepReadDataById() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/todostep/readDataById/1").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoStepGetAllData() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/todostep/getAllData").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoStepDelete() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/todostep/delete/10").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	// ToDoTask Controller
	
	@Test
	public void testToDoTaskSave() throws Exception {
		mockMvc.perform(post("http://localhost:8085/bluestone/todotask/save").contentType("application/json;charset=UTF-8").content("{\"id\":\"0\",\"stepId\":\"10\"}"))
				.andExpect(status().isOk())		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoTaskUpdate() throws Exception {
		mockMvc.perform(post("http://localhost:8085/bluestone/todotask/save").contentType("application/json;charset=UTF-8").content("{\"id\":\"1\",\"stepId\":\"10\"}"))
				.andExpect(status().isOk())		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoTaskReadDataById() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/todotask/readDataById/1").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoTaskGetAllData() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/todotask/getAllData").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoTaskMove() throws Exception {
		mockMvc.perform(post("http://localhost:8085/bluestone/todotask/moveStep").contentType("application/json;charset=UTF-8").content("{\"id\":\"1\",\"stepId\":\"9\"}"))
				.andExpect(status().isOk())		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	
	@Test
	public void testToDoTaskDelete() throws Exception {
		mockMvc.perform(get("http://localhost:8085/bluestone/todotask/delete/10").contentType("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.status").value("1"));
	}
}
