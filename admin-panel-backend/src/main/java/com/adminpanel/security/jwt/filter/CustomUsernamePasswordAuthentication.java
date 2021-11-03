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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.adminpanel.admin.dto.AdminDTO;
import com.adminpanel.admin.entity.Admin;
import com.adminpanel.admin.mapper.AdminMapper;
import com.adminpanel.admin.service.AdminService;
import com.adminpanel.exception.ApplicationException;
import com.adminpanel.security.jwt.AuthenticationRequest;
import com.adminpanel.security.jwt.JwtManager;
import com.adminpanel.security.mapper.ResponseMapper;
import com.adminpanel.security.user.dto.UserDTO;
import com.adminpanel.security.user.entity.User;
import com.adminpanel.security.user.service.UserService;
import com.adminpanel.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUsernamePasswordAuthentication extends UsernamePasswordAuthenticationFilter {

	private final JwtManager jwtManager;
	private final @Qualifier(BeanIds.AUTHENTICATION_MANAGER) AuthenticationManager authManager;

	private final UserService userService;
	private final AdminService adminService;
	private final ResponseMapper responseMapper;

	Logger logger = LoggerFactory.getLogger(CustomUsernamePasswordAuthentication.class);

	public CustomUsernamePasswordAuthentication(JwtManager jwtManager, AuthenticationManager manager,
			UserService userService, AdminService adminService, ResponseMapper responseMapper) {
		super();
		this.jwtManager = jwtManager;
		this.authManager = manager;
		this.userService = userService;
		this.adminService = adminService;
		this.responseMapper = responseMapper;

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			AuthenticationRequest authRequest = new ObjectMapper().readValue(request.getInputStream(),
					AuthenticationRequest.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					authRequest.getEmail(), authRequest.getPassword());
			return this.authManager.authenticate(authToken);
		} catch (IOException e) {

			ApplicationException exception = ApplicationException.builder(HttpStatus.BAD_REQUEST, e.getMessage());
			throw exception;
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String username = authResult.getName().toString();
		Map<Object, Object> resContent = new HashMap<Object, Object>();
		User user = userService.findUserByEmail(username);
		
		UserDTO userDTO = responseMapper.userToUserDTO(user);

		resContent.put("user_info", userDTO);

		switch (user.getUserType().getUserTypeName()) {
		case "ADMIN":
			Admin admin = adminService.findAdminById(user.getId());

			AdminDTO adminDto = responseMapper.adminToAdminDTO(admin);
			resContent.put("user_detail", adminDto);
			break;

		default:
			break;
		}

		Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
		Map<String, Object> tokenInfo = jwtManager.createAccessToken(username, authorities);

		resContent.put("access_token", tokenInfo);

		response.setContentType("application/json");

		response.getWriter().write(Utils.convertObjectToJson(resContent));

		// TODO Test with and without call super and dofilter internal
		// super.successfulAuthentication(request, response, chain, authResult);

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		if (failed instanceof BadCredentialsException) {
			ApplicationException applicationException = ApplicationException.builder(HttpStatus.UNAUTHORIZED,
					"Username or password is incorrect");
			throw applicationException;
		} else if (failed instanceof AccountStatusException) {

			ApplicationException applicationException = ApplicationException.builder(HttpStatus.UNAUTHORIZED,
					"account is not accessible");
			applicationException.setNextAction("Please Contact customer support at info@adminpanel.com");
			throw applicationException;

		} else {
			ApplicationException applicationException = ApplicationException.builder(HttpStatus.UNAUTHORIZED,
					"Please Try Again");
			applicationException
					.setNextAction("Please Contact customer support at info@adminpanel.com, for frequent error");
			throw applicationException;
		}

	}

}
