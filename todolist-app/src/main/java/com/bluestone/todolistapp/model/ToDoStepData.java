package com.bluestone.todolistapp.model;

public class ToDoStepData {
	private String id;
	private String name;
	private int position;
	private int watch;
	private String userId;
	
	public ToDoStepData() {
		super();
		this.id = "0";
		this.name = "";
		this.position = 1;
		this.watch = 0;
		this.userId = "0";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getWatch() {
		return watch;
	}
	public void setWatch(int watch) {
		this.watch = watch;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ToDoStepData [id=" + id + ", name=" + name + ", position=" + position + ", watch=" + watch + ", userId=" + userId + "]";
	}
	
}
