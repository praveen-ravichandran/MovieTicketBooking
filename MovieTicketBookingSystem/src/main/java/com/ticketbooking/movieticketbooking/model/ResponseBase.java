package com.ticketbooking.movieticketbooking.model;

import java.util.ArrayList;
import java.util.List;

import com.ticketbooking.movieticketbooking.model.error.ResponseErrorModel;
import com.ticketbooking.movieticketbooking.model.error.ResponseMessageModel;

public class ResponseBase {

	private List<ResponseErrorModel> errors;
	private List<ResponseMessageModel> messages;

	public List<ResponseErrorModel> getErrors() {
		return errors;
	}

	public void setErrors(List<ResponseErrorModel> errors) {
		this.errors = errors;
	}

	public void setErrors(ResponseErrorModel error) {
		List<ResponseErrorModel> errors = new ArrayList<>();
		errors.add(error);
		this.errors = errors;
	}

	public List<ResponseMessageModel> getMessages() {
		return messages;
	}

	public void setMessages(List<ResponseMessageModel> messages) {
		this.messages = messages;
	}

	public void setMessages(ResponseMessageModel message) {
		List<ResponseMessageModel> messages = new ArrayList<>();
		messages.add(message);
		this.messages = messages;
	}
}
