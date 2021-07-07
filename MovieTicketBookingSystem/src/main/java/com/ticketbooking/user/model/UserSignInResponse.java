package com.ticketbooking.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketbooking.common.ResponseBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSignInResponse extends ResponseBase {
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
