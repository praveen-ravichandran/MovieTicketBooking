package com.ticketbooking.bookingconfirmationbroker.model;

import java.sql.Timestamp;

public class ReviewTicketModel {
	
	private int bookingId;
	private int movieShowId;
	private int seatId;
	private Timestamp createdAt;
	private String ticketStatus;
	
	public int getBookingId() {
		return bookingId;
	}
	
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public int getMovieShowId() {
		return movieShowId;
	}
	
	public void setMovieShowId(int movieShowId) {
		this.movieShowId = movieShowId;
	}
	
	public int getSeatId() {
		return seatId;
	}
	
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
}
