package com.ticketbooking.movieticketbooking.constant;

public class DBQueryConst {

	public static final String GET_TICKETS_NOT_AVAILABLE = "SELECT DISTINCT(seat.seat_id) AS not_available_seat_id " + 
			"FROM movie_show ms " + 
			"LEFT JOIN theatre_hall hall ON hall.hall_id = ms.theatre_hall_id " + 
			"LEFT JOIN theatre_hall_class hall_class ON hall_class.theatre_hall_id = hall.hall_id " + 
			"LEFT JOIN theatre_hall_seat seat ON seat.hall_class_id = hall_class.class_id " + 
			"LEFT JOIN ticket_booking_seat bookseat ON seat.seat_id = bookseat.seat_id " + 
			"LEFT JOIN ticket_booking ticket ON ticket.booking_id = bookseat.booking_id " + 
			"LEFT JOIN ticket_status tstatus ON tstatus.status_id = ticket.ticket_status_id " + 
			"LEFT JOIN payment pay ON pay.ticket_booking_id = ticket.booking_id " + 
			"WHERE ms.show_id = ? AND ticket.movie_show_id = ? " + 
			"AND seat.seat_id IN (%s) " + 
			"AND tstatus.code <> 'REJECTED' " + 
			"AND ((tstatus.code = 'ON_REVIEW' AND TIMESTAMPDIFF(SECOND, ticket.created_at, NOW()) > 0 AND TIMESTAMPDIFF(SECOND, ticket.created_at, NOW()) <= 30) " + 
			"	OR (tstatus.code = 'CONFIRMED' AND pay.status = 'SUCCESS' AND TIMESTAMPDIFF(SECOND, ticket.confirmed_at, pay.action_time) <= 120) " + 
			"		OR (tstatus.code = 'CONFIRMED' AND (pay.status IS NULL OR pay.status <> 'SUCCESS') AND TIMESTAMPDIFF(SECOND, ticket.confirmed_at, NOW()) <= 120))";
		
	public static final String INSERT_TICKET = "INSERT INTO ticket_booking(unique_transaction_id, user_id, movie_show_id, ticket_status_id, amount, created_at) " +
			"VALUES (?,?,?,(SELECT status_id from ticket_status WHERE code = 'ON_REVIEW'),(SELECT SUM(ms.price + hall_class.base_price) as total_price " + 
			"FROM movie_show ms " + 
			"LEFT JOIN theatre_hall hall ON hall.hall_id = ms.theatre_hall_id " + 
			"LEFT JOIN theatre_hall_class hall_class ON hall_class.theatre_hall_id = hall.hall_id " + 
			"LEFT JOIN theatre_hall_seat seat ON seat.hall_class_id = hall_class.class_id " + 
			"WHERE ms.show_id = ? " + 
			"AND seat.seat_id IN (%s)),NOW())";
	
	public static final String INSERT_TICKET_SEATS = "INSERT INTO ticket_booking_seat(booking_id, seat_id)" +
			" VALUES ((SELECT booking_id FROM ticket_booking WHERE unique_transaction_id = ?),?)";

	public static final String GET_AVAILABLE_SEATS_FOR_MOVIE_SHOW = "SELECT distinct seat.*, ms.price + hall_class.base_price as price " + 
			"FROM movie_show ms " + 
			"LEFT JOIN theatre_hall hall ON hall.hall_id = ms.theatre_hall_id " + 
			"LEFT JOIN theatre_hall_class hall_class ON hall_class.theatre_hall_id = hall.hall_id " + 
			"LEFT JOIN theatre_hall_seat seat ON seat.hall_class_id = hall_class.class_id " + 
			"WHERE seat.seat_id NOT IN ( " + 
			"	SELECT distinct seat.seat_id " + 
			"	FROM movie_show ms " + 
			"	LEFT JOIN theatre_hall hall ON hall.hall_id = ms.theatre_hall_id " + 
			"	LEFT JOIN theatre_hall_class hall_class ON hall_class.theatre_hall_id = hall.hall_id " + 
			"	LEFT JOIN theatre_hall_seat seat ON seat.hall_class_id = hall_class.class_id " + 
			"	LEFT JOIN ticket_booking_seat bookseat ON seat.seat_id = bookseat.seat_id " + 
			"	LEFT JOIN ticket_booking ticket ON ticket.booking_id = bookseat.booking_id " + 
			"	LEFT JOIN ticket_status tstatus ON tstatus.status_id = ticket.ticket_status_id " + 
			"	LEFT JOIN payment pay ON pay.ticket_booking_id = ticket.booking_id " + 
			"	WHERE ms.show_id = ? AND ticket.movie_show_id = ? " + 
			"	AND tstatus.code <> 'REJECTED' " + 
			"	AND ((tstatus.code = 'ON_REVIEW' AND TIMESTAMPDIFF(SECOND, ticket.created_at, NOW()) > 0 AND TIMESTAMPDIFF(SECOND, ticket.created_at, NOW()) <= 30) " + 
			"		OR (tstatus.code = 'CONFIRMED' AND (pay.status IS NULL OR pay.status <> 'SUCCESS') AND TIMESTAMPDIFF(SECOND, ticket.confirmed_at, NOW()) <= 120) " + 
			"			OR (tstatus.code = 'CONFIRMED' AND pay.status = 'SUCCESS' AND TIMESTAMPDIFF(SECOND, ticket.confirmed_at, pay.action_time) <= 120) " + 
			"			) " + 
			") AND ms.show_id = ? " + 
			"ORDER BY row_num, col_num";

	public static final String GET_CLASS_FOR_MOVIE_SHOW = "SELECT distinct hall_class.* " + 
			"FROM movie_show ms " + 
			"LEFT JOIN theatre_hall hall ON hall.hall_id = ms.theatre_hall_id " + 
			"LEFT JOIN theatre_hall_class hall_class ON hall_class.theatre_hall_id = hall.hall_id " +
			"WHERE ms.show_id = ?";
	
}
