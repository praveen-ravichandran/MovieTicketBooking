package com.ticketbooking.movieticketbooking.model;

import java.util.List;

public class BookTicketModel {
	
	private String userMail;
	
	private int movieShowId;
	
	private String reqId;
	
	private List<Integer> seats;

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
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
