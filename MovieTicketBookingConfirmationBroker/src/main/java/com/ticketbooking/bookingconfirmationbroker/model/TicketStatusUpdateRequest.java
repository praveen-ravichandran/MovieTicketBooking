package com.ticketbooking.bookingconfirmationbroker.model;

public class TicketStatusUpdateRequest {
	
	public TicketStatusUpdateRequest(int booking_id, String status) {
		this.booking_id = booking_id;
		this.status = status;
	}

	private int booking_id;
	
	private String status;

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
