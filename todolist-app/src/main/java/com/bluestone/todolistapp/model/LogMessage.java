package com.bluestone.todolistapp.model;

public class LogMessage {
	private int httpStatus;
	private String httpMethod;
	private String path;
	private String clientIp;
	private String javaMethod;
	private String response;
	
	public LogMessage() {
		super();
	}
	
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getJavaMethod() {
		return javaMethod;
	}
	public void setJavaMethod(String javaMethod) {
		this.javaMethod = javaMethod;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
