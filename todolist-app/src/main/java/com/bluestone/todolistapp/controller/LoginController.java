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

import com.bluestone.todolistapp.model.LoginUserData;
import com.bluestone.todolistapp.model.ResponseData;
import com.bluestone.todolistapp.model.ResponseListData;
import com.bluestone.todolistapp.serviceImpl.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/bluestone/main")
public class LoginController extends IController {
	@Autowired
	LoginService service;

	@PostMapping(value="/save" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData<LoginUserData> save(@RequestBody LoginUserData data, HttpServletRequest request) throws JsonProcessingException {
		return service.save(data, getUser(request));
	}
	
	@GetMapping(value="/delete/{id}" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData<LoginUserData> delete(@PathVariable String id, HttpServletRequest request) throws JsonProcessingException{
		return service.delete(id, getUser(request));
	}
	
	@GetMapping(value="/getAllData" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseListData<LoginUserData> getAllData(HttpServletRequest request) throws JsonProcessingException{
		return service.getAllData(getUser(request));
	}
	
	@GetMapping(value="/readDataById/{id}" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData<LoginUserData> readDataById(@PathVariable String id, HttpServletRequest request) throws JsonProcessingException{
		return service.readDataById(id, getUser(request));
	}
	
	@PostMapping(value="/login" ,consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData<LoginUserData> login(@RequestBody LoginUserData data, HttpServletRequest request) throws JsonProcessingException {
		return service.login(data, getUser(request));
	}
}
