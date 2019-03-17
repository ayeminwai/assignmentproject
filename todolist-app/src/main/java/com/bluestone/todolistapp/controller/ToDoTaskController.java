package com.bluestone.todolistapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluestone.todolistapp.model.ResponseData;
import com.bluestone.todolistapp.model.ResponseListData;
import com.bluestone.todolistapp.model.ToDoTaskData;
import com.bluestone.todolistapp.serviceImpl.ToDoTaskService;

@RestController
@RequestMapping("/bluestone/todotask")
public class ToDoTaskController extends IController {
	@Autowired
	ToDoTaskService service;
	
	@PostMapping(value="/save",consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData<ToDoTaskData> save(@RequestBody ToDoTaskData data, HttpServletRequest request){
		return service.save(data, getUser(request));
	}
	
	@GetMapping(value="/delete/{id}" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData<ToDoTaskData> delete(@PathVariable String id, HttpServletRequest request){
		return service.delete(id, getUser(request));
	}
	
	@GetMapping(value="/getAllData" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseListData<ToDoTaskData> getAllData(HttpServletRequest request){
		return service.getAllData(getUser(request));
	}
	
	@GetMapping(value="/readDataById/{id}" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData<ToDoTaskData> readDataById(@PathVariable String id, HttpServletRequest request){
		return service.readDataById(id, getUser(request));
	}
	
	@PostMapping(value="/moveStep" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData<ToDoTaskData> moveStep(@RequestBody ToDoTaskData data, HttpServletRequest request){
		return service.moveStep(data, getUser(request));
	}
}
