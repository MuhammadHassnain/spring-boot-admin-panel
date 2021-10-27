package com.adminpanel.security.jwt;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtTokenInfo {
	String principle;
	Set<SimpleGrantedAuthority> authorities;
}
