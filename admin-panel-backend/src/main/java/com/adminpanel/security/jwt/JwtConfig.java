package com.adminpanel.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties("jwt")
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {
	
	private String secret;
	private Integer expireInDays;
	private String tokenPrefix;
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public Integer getExpireInDays() {
		return expireInDays;
	}
	public void setExpireInDays(Integer expireInDays) {
		this.expireInDays = expireInDays;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	

	
}
