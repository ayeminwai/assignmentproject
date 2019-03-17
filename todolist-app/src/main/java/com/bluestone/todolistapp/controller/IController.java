package com.bluestone.todolistapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.bluestone.todolistapp.model.UserData;
import com.bluestone.todolistapp.utility.ServerUtil;

@CrossOrigin
public class IController {
	public UserData getUser(HttpServletRequest request) {
		if(ServerUtil.IS_DEV)
			return new UserData();
		else
			return ServerUtil.getLoginUser(request);
	}
}
