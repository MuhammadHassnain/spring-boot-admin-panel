package com.adminpanel.security.jwt;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

@Configuration
public class TokenSecretKey {

	private final JwtConfig jwtConfig;

	@Autowired
	public TokenSecretKey(JwtConfig jwtConfig) {
		super();
		this.jwtConfig = jwtConfig;
	}
	
	
	
	@Bean SecretKey secretKey() {
		return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
	}
}
