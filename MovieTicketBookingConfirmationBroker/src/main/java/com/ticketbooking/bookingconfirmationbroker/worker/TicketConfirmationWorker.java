package com.ticketbooking.bookingconfirmationbroker.worker;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ticketbooking.bookingconfirmationbroker.business.TicketWorkerBusiness;
import com.ticketbooking.bookingconfirmationbroker.model.ReviewTicketModel;
import com.ticketbooking.bookingconfirmationbroker.model.TicketStatusUpdateRequest;

@EnableScheduling
public class TicketConfirmationWorker {

	@Autowired
	TicketWorkerBusiness ticketWorkerBusiness;

	@Scheduled(fixedDelay = 1000)
	public void ticketConfirmer() {
		System.out.println("\nticketConfirmer execution start. Current time is :: " + new Date());
		
		List<ReviewTicketModel> reviewTickets = ticketWorkerBusiness.getReviewTickets();
		List<TicketStatusUpdateRequest> ticketStatutes = ticketWorkerBusiness.processTickets(reviewTickets);
		
		if(ticketStatutes.size() > 0) {
			System.out.println(ticketStatutes.size()+" tickets processing.");
			ticketWorkerBusiness.updateBookingStatus(ticketStatutes);
		} else {
			System.out.println("No Tickets to process.");
		}
		System.out.println("ticketConfirmer execution end. Current time is :: " + new Date()+"\n");
	}

}
