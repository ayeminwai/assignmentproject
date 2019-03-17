package com.bluestone.todolistapp.model;

public class ToDoTaskData {
	private String id;
	private String name;
	private String dateTime;
	private String stepId;
	
	public ToDoTaskData() {
		super();
		this.id = "0";
		this.name = "";
		this.dateTime = "";
		this.stepId = "0";
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	
}
