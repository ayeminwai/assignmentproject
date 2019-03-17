package com.bluestone.todolistapp.model;

import com.bluestone.todolistapp.utility.StatusUtil;

public class ResponseData<T> {
	private String message;
	private int status;
	private String token;
	private T result;
	
	public ResponseData() {
		super();
		this.message = StatusUtil.RepStatus.FAIL.name();
		this.status = 0;
		this.token = "";
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
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "ResponseData [message=" + message + ", status=" + status + ", token=" + token + ", result=" + result + "]";
	}
}
