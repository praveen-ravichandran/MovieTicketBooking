package com.ticketbooking.user.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.ticketbooking.common.HmacService;
import com.ticketbooking.user.dao.UserDao;
import com.ticketbooking.user.model.UserModel;

public class UserBusiness {

	@Autowired
	UserDao userDao;
	
	public UserModel getUser(String userName, String password) {
		UserModel user = userDao.getUserPassword(userName);
		if(user != null) {
			HmacService hmacService = new HmacService(System.getenv("USER_PWD_HMAC_KEY"));
			if(hmacService.checkHmac(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

}
