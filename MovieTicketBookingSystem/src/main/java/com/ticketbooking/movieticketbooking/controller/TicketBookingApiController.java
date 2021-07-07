package com.ticketbooking.movieticketbooking.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticketbooking.common.error.ResponseErrorModel;
import com.ticketbooking.common.error.ResponseMessageModel;
import com.ticketbooking.movieticketbooking.business.TicketBookingBusiness;
import com.ticketbooking.movieticketbooking.constant.Constants;
import com.ticketbooking.movieticketbooking.model.BookTicketModel;
import com.ticketbooking.movieticketbooking.model.BookTicketResponse;
import com.ticketbooking.movieticketbooking.model.GetAvailableSeatsResponse;
import com.ticketbooking.security.common.JwtTokenHandler;

@Controller
public class TicketBookingApiController {
	
	@Autowired
	TicketBookingBusiness ticketBookingBusiness;
	
	private final String PREFIX = "Bearer ";
	
	@RequestMapping(value = Constants.GET_AVAILABLE_SEATS_API, method = RequestMethod.GET)
	public @ResponseBody GetAvailableSeatsResponse getAvailableSeats(@PathVariable("movieShowId") int movieShowId) {
		return ticketBookingBusiness.getAvailableSeats(movieShowId);
	}
	
	@RequestMapping(value = Constants.BOOK_TICKET_API, method = RequestMethod.POST)
	public @ResponseBody BookTicketResponse saveTicket(@RequestHeader String authorization, @RequestBody BookTicketModel ticket) {
		BookTicketResponse response = new BookTicketResponse();
		if(!ticketBookingBusiness.isTicketValid(ticket)) {
			response.setErrors(new ResponseErrorModel(Constants.ERR_BOOKING_INVALID_CODE, Constants.ERR_BOOKING_INVALID_DESC));
		} else {
			List<Integer> nonAvailableSeats = ticketBookingBusiness.checkAndGetNonAvailableTickets(ticket);
			if(nonAvailableSeats.size() > 0) {
				response.setMessages(
						new ResponseMessageModel(Constants.MSG_SEAT_NOT_AVAILABLE_CODE, 
								String.format(Constants.MSG_SEAT_NOT_AVAILABLE_DESC
										, nonAvailableSeats.toString()
										, nonAvailableSeats.size() > 1 ? "are" : "is")));
			} else {
				ticket.setReqId(UUID.randomUUID().toString());
				ticket.setUserId(
						Integer.parseInt(JwtTokenHandler.getJwtClaims(authorization.replaceFirst(PREFIX, "")).get("id").toString()));
				List<Integer> results = ticketBookingBusiness.bookTicket(ticket);
				if(results.stream().anyMatch(i -> i == 0)) {
					response.setMessages(
							new ResponseMessageModel(Constants.ERR_BOOKING_CODE,Constants.ERR_BOOKING_DESC));
				} else {
					response.setReqId(ticket.getReqId());
				}
			}
		}
		return response;
	}
}
