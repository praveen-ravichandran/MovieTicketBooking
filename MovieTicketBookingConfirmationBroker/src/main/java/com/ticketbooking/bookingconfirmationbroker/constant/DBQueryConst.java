package com.ticketbooking.bookingconfirmationbroker.constant;

public class DBQueryConst {

	public static final String  GET_REVIEW_TICKETS = "SELECT ticket.booking_id, ticket.movie_show_id, bookseat.seat_id, ticket.created_at " + 
			"FROM ticket_booking ticket " + 
			"LEFT JOIN ticket_booking_seat bookseat ON ticket.booking_id = bookseat.booking_id " + 
			"LEFT JOIN ticket_status tstatus ON tstatus.status_id = ticket.ticket_status_id " + 
			"WHERE tstatus.code = 'ON_REVIEW'";
		
	public static final String INSERT_TICKET = "INSERT INTO ticket_booking(unique_transaction_id, user_mail, movie_show_id, ticket_status_id, amount, created_at) " +
			"VALUES (?,?,?,(SELECT status_id from ticket_status WHERE code = 'ON_REVIEW'),(SELECT SUM(ms.price + hall_class.base_price) as total_price " + 
			"FROM movie_show ms " + 
			"LEFT JOIN theatre_hall hall ON hall.hall_id = ms.theatre_hall_id " + 
			"LEFT JOIN theatre_hall_class hall_class ON hall_class.theatre_hall_id = hall.hall_id " + 
			"LEFT JOIN theatre_hall_seat seat ON seat.hall_class_id = hall_class.class_id " + 
			"WHERE ms.show_id = ? " + 
			"AND seat.seat_id IN (%s)),NOW())";
	
	public static final String INSERT_TICKET_SEATS = "INSERT INTO ticket_booking_seat(booking_id, seat_id)" +
			" VALUES ((SELECT booking_id FROM ticket_booking WHERE unique_transaction_id = ?),?)";

	public static final String UPDATE_REVIEW_TICKETS = "UPDATE ticket_booking " + 
			"SET " + 
			"ticket_status_id = (SELECT status_id FROM ticket_status WHERE code = ?), " + 
			"confirmed_at = ? " + 
			"WHERE booking_id = ?";
	
}
