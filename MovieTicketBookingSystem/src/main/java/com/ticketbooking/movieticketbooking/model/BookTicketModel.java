package com.ticketbooking.movieticketbooking.model;

import java.util.List;

public class BookTicketModel {
	
	private int userId;
	
	private int movieShowId;
	
	private String reqId;
	
	private List<Integer> seats;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMovieShowId() {
		return movieShowId;
	}

	public void setMovieShowId(int movieShowId) {
		this.movieShowId = movieShowId;
	}

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
}
