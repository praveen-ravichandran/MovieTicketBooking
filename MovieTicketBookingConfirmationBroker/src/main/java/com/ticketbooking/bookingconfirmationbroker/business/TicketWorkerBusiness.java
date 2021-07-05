package com.ticketbooking.bookingconfirmationbroker.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ticketbooking.bookingconfirmationbroker.dao.TicketWorkerDao;
import com.ticketbooking.bookingconfirmationbroker.model.ReviewTicketModel;
import com.ticketbooking.bookingconfirmationbroker.model.TicketStatusUpdateRequest;

public class TicketWorkerBusiness {
	
	@Autowired
	TicketWorkerDao ticketWorkerDao;

	public List<ReviewTicketModel> getReviewTickets() {
		return ticketWorkerDao.getReviewTickets();
	}

	public List<TicketStatusUpdateRequest> processTickets(List<ReviewTicketModel> reviewTickets) {
		List<Integer> confirmedBookingIdList = new ArrayList<>();
		List<Integer> rejectedBookingIdList = new ArrayList<>();
		
		Map<Integer, List<Integer>> confirmedSeatsForMovieShows = new HashMap<>();
		Map<Integer, Long> bookingIdWithSeatsCount = reviewTickets.stream()
			.collect(Collectors.groupingBy(ReviewTicketModel::getBookingId, Collectors.counting()));
		
		reviewTickets.stream()
			.sorted(Comparator
					.comparing(ReviewTicketModel::getCreatedAt)
					.thenComparing(ReviewTicketModel::getBookingId))
			.forEach(t -> {
					if(!confirmedBookingIdList.contains(t.getBookingId()) && !rejectedBookingIdList.contains(t.getBookingId())) {
						if(((new Date().getTime() - t.getCreatedAt().getTime())/1000) > 30) {
							rejectedBookingIdList.add(t.getBookingId());
							return;
						}
						
						Boolean isAlreadyConfirmed = false;
						
						List<Integer> currentSeatsForMovieShow = reviewTickets.stream()
								.filter(p -> p.getBookingId() == t.getBookingId())
								.map(ReviewTicketModel::getSeatId)
								.distinct()
								.collect(Collectors.toList());
						
						if(currentSeatsForMovieShow.get(0) != 0 && Objects.nonNull(confirmedSeatsForMovieShows.get(t.getMovieShowId()))) {
							for(int i = 0; i < confirmedSeatsForMovieShows.get(t.getMovieShowId()).size(); i++) {
								if(currentSeatsForMovieShow.contains(confirmedSeatsForMovieShows.get(t.getMovieShowId()).get(i))) {
									isAlreadyConfirmed = true;
									break;
								}
							}
						}
						
						if(isAlreadyConfirmed || currentSeatsForMovieShow.get(0) == 0) {
							rejectedBookingIdList.add(t.getBookingId());
						} else {
							List<ReviewTicketModel> concurrentBooking = reviewTickets.stream()
								.filter(f -> t.getBookingId() != f.getBookingId() 
									&& t.getMovieShowId() == f.getMovieShowId() && t.getCreatedAt().equals(f.getCreatedAt())
									&& currentSeatsForMovieShow.contains(f.getSeatId()))
								.collect(Collectors.toList());
							
							if(Objects.isNull(confirmedSeatsForMovieShows.get(t.getMovieShowId()))) {
								confirmedSeatsForMovieShows.put(t.getMovieShowId(), new ArrayList<>());
							}

							if(concurrentBooking.isEmpty()) {
								confirmedBookingIdList.add(t.getBookingId());
								confirmedSeatsForMovieShows.get(t.getMovieShowId()).addAll(reviewTickets.stream()
									.filter(p -> p.getMovieShowId() == t.getMovieShowId() && p.getBookingId() == t.getBookingId())
									.map(ReviewTicketModel::getSeatId)
									.collect(Collectors.toList()));
							} else {
								List<Integer> concurrentBookingIds = concurrentBooking.stream()
										.map(ReviewTicketModel::getBookingId)
										.distinct()
										.collect(Collectors.toList());
								
								Map<Integer, Long> concurrentBookingWithSeatCountOrdered = bookingIdWithSeatsCount.entrySet().stream()
									.filter(p -> concurrentBookingIds.contains(p.getKey()))
									.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
									.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
							                LinkedHashMap::new));
								
								concurrentBookingWithSeatCountOrdered.entrySet().stream()
									.limit(1)
									.forEach(r -> {
											confirmedBookingIdList.add(r.getKey());
											confirmedSeatsForMovieShows.get(t.getMovieShowId()).addAll(reviewTickets.stream()
													.filter(p -> p.getMovieShowId() == t.getMovieShowId() && p.getBookingId() == r.getKey())
													.map(ReviewTicketModel::getSeatId)
													.collect(Collectors.toList()));
										});
								
								
								concurrentBookingWithSeatCountOrdered.entrySet().stream()
									.skip(1)
									.forEach(r -> rejectedBookingIdList.add(r.getKey()));
							}
						}
						
				}
			});
				
		List<TicketStatusUpdateRequest> ticketStatusList = new ArrayList<>();
		
		for(int i = 0; i < confirmedBookingIdList.size(); i++) {			
			ticketStatusList.add(new TicketStatusUpdateRequest(confirmedBookingIdList.get(i), "CONFIRMED"));
		}
		
		for(int i = 0; i < rejectedBookingIdList.size(); i++) {			
			ticketStatusList.add(new TicketStatusUpdateRequest(rejectedBookingIdList.get(i), "REJECTED"));
		}
		
		return ticketStatusList;
	}

	public void updateBookingStatus(List<TicketStatusUpdateRequest> ticketStatutes) {
		ticketWorkerDao.updateBookingStatus(ticketStatutes);
	}

}
