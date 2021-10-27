package com.adminpanel.security.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.adminpanel.exception.ApplicationException;
import com.adminpanel.util.Utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtManager {
	
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	
	@Autowired
	public JwtManager(JwtConfig config, SecretKey secretKey) {
		this.jwtConfig = config;
		this.secretKey =  secretKey;
	}
	
	
	public JwtConfig getJwtConfig() {
		return jwtConfig;
	}
	
	
	
	public Map<String,Object> createAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {
		
		Map<String, Object> tokenInfo = new HashMap<String, Object>();
		
		Date expireDate = Utils.changeDays(new Date(), jwtConfig.getExpireInDays());
		Date currDate = new Date();
		String token = Jwts.builder()
							.setSubject(username)
							.claim("authorities",authorities)
							.setIssuedAt(currDate)
							.setExpiration(expireDate)
							.signWith(secretKey)
							.compact();
		
		tokenInfo.put("token", token );
		tokenInfo.put("iat",Utils.formatDateTime(currDate));
		tokenInfo.put("exp", Utils.formatDateTime(expireDate));
		tokenInfo.put("tokenPrefix", jwtConfig.getTokenPrefix());
		tokenInfo.put("username", username);
		tokenInfo.put("authorities", authorities);
		return tokenInfo;
	}
	
	
	

	public JwtTokenInfo validateAccessToken(String token) {
		
		if(!token.startsWith(jwtConfig.getTokenPrefix())) {
			throw ApplicationException.builder(HttpStatus.BAD_REQUEST, "Invalid Token, Token Prefix Must be "+ jwtConfig.getTokenPrefix());
		}
		
		
		try {
			token = token.replace(jwtConfig.getTokenPrefix(), "");
			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);
			
			Claims claimsBody = claimsJws.getBody();
			
			String subject = claimsBody.getSubject();
			
			@SuppressWarnings("unchecked")
			List<Map<String, String>> authorities = (List<Map<String, String>>)claimsBody.get("authorities");
			
			Set<SimpleGrantedAuthority> authSet = authorities.stream().map(item -> 
				new SimpleGrantedAuthority(item.get("authority"))).collect(Collectors.toSet());
			
			JwtTokenInfo tokenInfo = new JwtTokenInfo(subject,authSet);
			
			
			return tokenInfo;
			
		}catch(ExpiredJwtException ex) {
			throw ApplicationException.builder(HttpStatus.UNAUTHORIZED, "Expired Token").setNextAction("Login Again");
		}catch(UnsupportedJwtException | MalformedJwtException| SignatureException| IllegalArgumentException ex) {
			throw ApplicationException.builder(HttpStatus.UNAUTHORIZED, "Token Verification Failed").setNextAction("Login Again");
		}
	}
	
	
	
	
	

}
