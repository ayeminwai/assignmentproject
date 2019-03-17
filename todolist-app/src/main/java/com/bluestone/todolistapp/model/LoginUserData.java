package com.bluestone.todolistapp.model;

public class LoginUserData {
	private String id;
	private String code;
	private String name;
	private String password;
	private String email;
	private String address;
	private String phone;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LoginUserData() {
		super();
		this.id = "0";
		this.code = "";
		this.name = "";
		this.password = "";
		this.email = "";
		this.address = "";
		this.phone = "";
	}
	
	@Override
	public String toString() {
		return "LoginUserData [id=" + id + ", code=" + code + ", name=" + name + ", password=" + password + ", email=" + email + ", address=" + address + ", phone=" + phone + "]";
	}
}
