package com.ticketbooking.bookingconfirmationbroker.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.ticketbooking.bookingconfirmationbroker.constant.DBQueryConst;
import com.ticketbooking.bookingconfirmationbroker.model.ReviewTicketModel;
import com.ticketbooking.bookingconfirmationbroker.model.TicketStatusUpdateRequest;

public class TicketWorkerDao {

	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<ReviewTicketModel> getReviewTickets() {
		return template.query(DBQueryConst.GET_REVIEW_TICKETS, new RowMapper<ReviewTicketModel>() {
			public ReviewTicketModel mapRow(ResultSet rs, int row) throws SQLException {
				ReviewTicketModel ticket = new ReviewTicketModel();
				ticket.setBookingId(rs.getInt("booking_id"));
				ticket.setMovieShowId(rs.getInt("movie_show_id"));
				ticket.setSeatId(rs.getInt("seat_id"));
				ticket.setCreatedAt(rs.getTimestamp("created_at"));
				return ticket;
			}
		});
	}

	@Transactional
	public void updateBookingStatus(List<TicketStatusUpdateRequest> ticketStatutes) {
		 template.batchUpdate(DBQueryConst.UPDATE_REVIEW_TICKETS, 
				new BatchPreparedStatementSetter() {

		            public void setValues(PreparedStatement ps, int i) 
		                throws SQLException {
		                ps.setString(1, ticketStatutes.get(i).getStatus());
		                ps.setTimestamp(2, 
		                		ticketStatutes.get(i).getStatus() == "CONFIRMED" ? new Timestamp(new Date().getTime()) : null);
		                ps.setInt(3, ticketStatutes.get(i).getBooking_id());
		            }
		
		            public int getBatchSize() {
		                return ticketStatutes.size();
		            }
		            
		     	});
	}
	
}
