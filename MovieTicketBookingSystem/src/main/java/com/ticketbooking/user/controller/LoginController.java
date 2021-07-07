package com.ticketbooking.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticketbooking.common.error.ResponseErrorModel;
import com.ticketbooking.security.common.JwtTokenHandler;
import com.ticketbooking.user.business.UserBusiness;
import com.ticketbooking.user.constant.Constants;
import com.ticketbooking.user.model.UserModel;
import com.ticketbooking.user.model.UserSignInRequest;
import com.ticketbooking.user.model.UserSignInResponse;

@Controller
public class LoginController {
	
	@Autowired
	UserBusiness userBusiness;
	
	@RequestMapping(value = Constants.USER_SIGNIN_API, method = RequestMethod.POST)
	public @ResponseBody UserSignInResponse userSignIn(@RequestBody UserSignInRequest signInRequest) {
		UserSignInResponse response = new UserSignInResponse();
		UserModel user = userBusiness.getUser(signInRequest.getUserName(), signInRequest.getPassword());
		if(user != null) {
			response.setAccessToken(
					JwtTokenHandler.createJwtSignedHMAC(String.valueOf(user.getUserId()), signInRequest.getUserName()));
			return response;
		}
		response.setErrors(new ResponseErrorModel("SIGNIN_ERROR", "User SignIn Error."));
		return response;
	}
	
}
