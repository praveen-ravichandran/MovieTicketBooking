package com.ticketbooking.movieticketbooking.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.ticketbooking.movieticketbooking.constant.DBQueryConst;
import com.ticketbooking.movieticketbooking.model.BookTicketModel;

public class TicketBookingDao {

	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Integer> checkAndGetNonAvailableTickets(BookTicketModel ticket) {
		return template.query(String.format(DBQueryConst.GET_TICKETS_NOT_AVAILABLE,
				ticket.getSeats().stream().map(i->Integer.toString(i)).collect(Collectors.joining(", "))), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, ticket.getMovieShowId());
				ps.setInt(2, ticket.getMovieShowId());
			}
			
		}, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int row) throws SQLException {
				return rs.getInt("not_available_seat_id");
			}
		});
	}

	@Transactional
	public List<Integer> bookTicket(BookTicketModel ticket) {
		List<Integer> result = new ArrayList<Integer>();
		result.add(
			template.update(String.format(DBQueryConst.INSERT_TICKET,
				ticket.getSeats().stream().map(i->Integer.toString(i)).collect(Collectors.joining(", "))),
			new PreparedStatementSetter() {
	
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, ticket.getReqId());
					ps.setString(2, ticket.getUserMail());
					ps.setInt(3, ticket.getMovieShowId());
					ps.setInt(4, ticket.getMovieShowId());
				}
				
			})
		);
		result.addAll(
			Arrays.stream(template.batchUpdate(DBQueryConst.INSERT_TICKET_SEATS,
	            new BatchPreparedStatementSetter() {
	                 
	                @Override
	                public void setValues(PreparedStatement ps, int i) throws SQLException {
	                    ps.setString(1, ticket.getReqId());
	                    ps.setInt(2, ticket.getSeats().get(i));
	                }
	                 
	                @Override
	                public int getBatchSize() {
	                    return ticket.getSeats().size();
	                }
	            })).boxed().collect(Collectors.toList())
			);
		return result;
	}

}
