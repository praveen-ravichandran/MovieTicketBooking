package com.ticketbooking.movieticketbooking.model;

public class TheatreHallSeatModel {
	private int seatId;
	
	private int rowNum;
	
	private int colNum;
	
	private String seatRowCode;
	
	private String seatColCode;
	
	private int hallClassId;
	
	private float price;

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public String getSeatRowCode() {
		return seatRowCode;
	}

	public void setSeatRowCode(String seatRowCode) {
		this.seatRowCode = seatRowCode;
	}

	public String getSeatColCode() {
		return seatColCode;
	}

	public void setSeatColCode(String seatColCode) {
		this.seatColCode = seatColCode;
	}

	public int getHallClassId() {
		return hallClassId;
	}

	public void setHallClassId(int hallClassId) {
		this.hallClassId = hallClassId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
