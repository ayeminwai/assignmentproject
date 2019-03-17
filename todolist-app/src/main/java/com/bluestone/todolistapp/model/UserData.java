package com.bluestone.todolistapp.model;

public class UserData {
	private String id;
	private String code;
	private String name;
	private String orgId;
	
	public UserData() {
		super();
		this.id = "0";
		this.code = "";
		this.name = "";
		this.orgId = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
}
