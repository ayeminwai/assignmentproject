package com.bluestone.todolistapp.model;

import java.util.ArrayList;
import java.util.List;

import com.bluestone.todolistapp.utility.StatusUtil;

public class ResponseListData<T> {
	private String message;
	private int status;
	private String token;
	private List<T> result;
	
	public ResponseListData() {
		super();
		this.message = StatusUtil.RepStatus.FAIL.name();
		this.status = 0;
		this.token = "";
		this.result = new ArrayList<>();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
}
