package com.adminpanel.security.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adminpanel.security.jwt.JwtManager;
import com.adminpanel.security.jwt.JwtTokenInfo;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthFilter.class);
	
	
	private JwtManager jwtManager;
	
	
	
	
	public UsernamePasswordAuthFilter(JwtManager jwtManager) {
		super();
		this.jwtManager = jwtManager;
	}




	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		logger.info("doFilterInternal()");
		
		
		//1. Fetch info 
		
		String token = request.getHeader("authorization");
		
		if(token==null) {
			token = request.getHeader("Authorization");
			if(token == null) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		
		
		JwtTokenInfo accessToken = jwtManager.validateAccessToken(token);
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(accessToken.getPrinciple(),null,accessToken.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
		filterChain.doFilter(request, response);
		
	}

}
