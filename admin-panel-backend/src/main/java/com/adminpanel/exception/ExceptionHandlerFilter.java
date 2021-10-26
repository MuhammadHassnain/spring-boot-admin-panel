package com.adminpanel.exception;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.adminpanel.util.Utils;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			filterChain.doFilter(request, response);
		}catch(ApplicationException ex) {
			
			response.setStatus(ex.getHttpStatus().value());
			response.setContentType("application/json");
			response.getWriter().write(Utils.convertObjectToJson(ExceptionResponse.createReponseFromException(ex)));
			
			
		}
	}

}
