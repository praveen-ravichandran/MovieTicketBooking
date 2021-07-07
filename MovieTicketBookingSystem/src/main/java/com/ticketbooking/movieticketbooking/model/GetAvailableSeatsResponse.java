package com.ticketbooking.movieticketbooking.model;

import java.util.List;

public class GetAvailableSeatsResponse {
	
	private int movieShowId;

	private List<TheatreHallClassModel> theatreHallClasses;
	
	private List<TheatreHallSeatModel> availableSeats;

	public int getMovieShowId() {
		return movieShowId;
	}

	public void setMovieShowId(int movieShowId) {
		this.movieShowId = movieShowId;
	}

	public List<TheatreHallClassModel> getTheatreHallClasses() {
		return theatreHallClasses;
	}

	public void setTheatreHallClasses(List<TheatreHallClassModel> theatreHallClasses) {
		this.theatreHallClasses = theatreHallClasses;
	}

	public List<TheatreHallSeatModel> getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(List<TheatreHallSeatModel> availableSeats) {
		this.availableSeats = availableSeats;
	}

}
