package com.adminpanel.security.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.adminpanel.util.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtManager {
	
	private final JwtConfig jwtConfig;
	
	
	@Autowired
	public JwtManager(JwtConfig config) {
		this.jwtConfig = config;
	}
	
	
	public JwtConfig getJwtConfig() {
		return jwtConfig;
	}
	
	
	
	public Map<String,Object> createAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {
		
		Map<String, Object> tokenInfo = new HashMap<String, Object>();
		
		SecretKey key  = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
		Date expireDate = Utils.changeDays(new Date(), jwtConfig.getExpireInDays());
		Date currDate = new Date();
		String token = Jwts.builder()
							.setSubject(username)
							.claim("authorities",authorities)
							.setIssuedAt(currDate)
							.setExpiration(expireDate)
							.signWith(key)
							.compact();
		
		tokenInfo.put("token", token );
		tokenInfo.put("iat",Utils.formatDateTime(currDate));
		tokenInfo.put("exp", Utils.formatDateTime(expireDate));
		tokenInfo.put("tokenPrefix", jwtConfig.getTokenPrefix());
		tokenInfo.put("username", username);
		tokenInfo.put("authorities", authorities);
		return tokenInfo;
	}
	
	
	
	public void verifyToken(String token) {
		throw new UnsupportedOperationException();		
	}
	
	
	
	
	

}
