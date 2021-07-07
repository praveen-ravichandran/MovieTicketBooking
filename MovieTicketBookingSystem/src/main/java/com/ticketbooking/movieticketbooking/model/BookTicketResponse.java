package com.ticketbooking.movieticketbooking.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketbooking.common.ResponseBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookTicketResponse extends ResponseBase {
	
	private String reqId;

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

}
