package com.ticketbooking.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.ticketbooking.security.common.JwtTokenHandler;

public class AuthorizationFilter extends OncePerRequestFilter {

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader != null && authenticationHeader.startsWith(PREFIX)) {
			if(JwtTokenHandler.isTokenValid(authenticationHeader.replaceFirst(PREFIX, ""))) {
				chain.doFilter(request, response);
			}
		}
		response.setStatus(401);
	}

}
