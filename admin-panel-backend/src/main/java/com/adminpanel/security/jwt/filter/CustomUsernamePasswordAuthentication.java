package com.adminpanel.security.jwt.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.adminpanel.exception.ApplicationException;
import com.adminpanel.security.jwt.AuthenticationRequest;
import com.adminpanel.security.jwt.JwtManager;
import com.adminpanel.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CustomUsernamePasswordAuthentication extends UsernamePasswordAuthenticationFilter{
	
	private final JwtManager jwtManager;
	private final AuthenticationManager authManager;
	
	Logger logger = LoggerFactory.getLogger(CustomUsernamePasswordAuthentication.class);
		
	public CustomUsernamePasswordAuthentication(JwtManager jwtManager,AuthenticationManager manager) {
		super();
		this.jwtManager = jwtManager;
		this.authManager = manager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			AuthenticationRequest authRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
			return this.authManager.authenticate(authToken);
		} catch (IOException e) {
			
			ApplicationException exception = ApplicationException.builder(HttpStatus.BAD_REQUEST,e.getMessage());
			throw exception;
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String username = authResult.getName().toString();
		Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
		
		Map<String, Object> tokenInfo = jwtManager.createAccessToken(username,authorities);
		

		Map<Object, Object> resContent = new HashMap<Object, Object>();
		
		resContent.put("access_token", tokenInfo);
	
		response.setContentType("application/json");
		
		response.getWriter().write(Utils.convertObjectToJson(resContent));
		
		// TODO Test with and without call super and dofilter internal
		//super.successfulAuthentication(request, response, chain, authResult);
		
		
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.unsuccessfulAuthentication(request, response, failed);
	}
	
	

}
