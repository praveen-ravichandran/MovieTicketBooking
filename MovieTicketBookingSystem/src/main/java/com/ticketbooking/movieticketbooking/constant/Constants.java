package com.ticketbooking.movieticketbooking.constant;

public class Constants {
	
	public static final String GET_AVAILABLE_SEATS_API = "/api/movieshow/{movieShowId}/seatsavailable";
	public static final String BOOK_TICKET_API = "/api/ticket/book";
	
	public static final String MSG_SEAT_NOT_AVAILABLE_CODE = "MSG_SEAT_NOT_AVAILABLE";
	public static final String MSG_SEAT_NOT_AVAILABLE_DESC = "Oops! Seat: %s %s not currently available.";
		
	public static final String ERR_BOOKING_CODE = "ERR_BOOKING_CODE";
	public static final String ERR_BOOKING_DESC = "Sorry! Something went wrong. Please try again later.";
	
	public static final String ERR_BOOKING_INVALID_CODE = "ERR_BOOKING_INVALID_CODE";
	public static final String ERR_BOOKING_INVALID_DESC = "Ticket Booking data is invalid!";
	
}
