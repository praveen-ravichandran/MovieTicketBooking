package com.ticketbooking.movieticketbooking.model;

public class TheatreHallClassModel {
	private int classId;
	
	private String name;
	
	private String description;
	
	private String classType;
	
	private int classOrder;
	
	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getClassOrder() {
		return classOrder;
	}

	public void setClassOrder(int classOrder) {
		this.classOrder = classOrder;
	}

}
