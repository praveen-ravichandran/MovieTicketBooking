package com.ticketbooking.movieticketbooking.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ticketbooking.movieticketbooking.dao.TicketBookingDao;
import com.ticketbooking.movieticketbooking.model.BookTicketModel;

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
		if(ticket.getUserMail() == null || ticket.getUserMail().isEmpty()) {
			return false;
		}
		if(ticket.getSeats() == null || ticket.getSeats().size() == 0 || ticket.getSeats().size() > 6) {
			return false;
		}
		return true;
	}

}
