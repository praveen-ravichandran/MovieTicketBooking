package com.ticketbooking.movieticketbooking.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ticketbooking.movieticketbooking.dao.TicketBookingDao;
import com.ticketbooking.movieticketbooking.model.BookTicketModel;
import com.ticketbooking.movieticketbooking.model.GetAvailableSeatsResponse;

public class TicketBookingBusiness {
	
	@Autowired
	TicketBookingDao ticketBookingDao;

	public List<Integer> bookTicket(BookTicketModel ticket) {
		return ticketBookingDao.bookTicket(ticket);
	}

	public List<Integer> checkAndGetNonAvailableTickets(BookTicketModel ticket) {
		return ticketBookingDao.checkAndGetNonAvailableTickets(ticket);
	}

	public boolean isTicketValid(BookTicketModel ticket) {
		if(ticket.getMovieShowId() <= 0) {
			return false;
		}
		if(ticket.getSeats() == null || ticket.getSeats().size() == 0 || ticket.getSeats().size() > 6) {
			return false;
		}
		return true;
	}

	public GetAvailableSeatsResponse getAvailableSeats(int movieShowId) {
		GetAvailableSeatsResponse getAvailableSeatsResponse = new GetAvailableSeatsResponse();
		getAvailableSeatsResponse.setMovieShowId(movieShowId);
		getAvailableSeatsResponse.setAvailableSeats(ticketBookingDao.getAvailableSeatsForMovieShow(movieShowId));
		getAvailableSeatsResponse.setTheatreHallClasses(ticketBookingDao.getClassForMovieShow(movieShowId));
		return getAvailableSeatsResponse;
	}

}
