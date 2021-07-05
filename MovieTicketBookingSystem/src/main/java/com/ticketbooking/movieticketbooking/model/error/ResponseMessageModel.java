package com.ticketbooking.movieticketbooking.model.error;

public class ResponseMessageModel {

	private String messageCode;
	private String description;

	public ResponseMessageModel(String messageCode, String description) {
		this.messageCode = messageCode;
		this.description = description;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
