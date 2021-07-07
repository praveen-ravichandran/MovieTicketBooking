package com.ticketbooking.user.constant;

public class DBQueryConst {

	public static final String GET_USER_PASSWORD = "SELECT user_id, password FROM user " + 
			"WHERE email_address = ?";
			
}
