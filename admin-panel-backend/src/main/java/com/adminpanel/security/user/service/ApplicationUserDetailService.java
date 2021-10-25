package com.adminpanel.security.user.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ApplicationUserDetailService  implements UserDetailsService{

	@Autowired PasswordEncoder passwordEncoder;
	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.adminpanel.security.user.entity.User user = userService.findUserByEmail(username);
		
		if(user!=null) {
			Set<SimpleGrantedAuthority> authorities = user.getAuthorities()
															.stream()
															.map(ele -> new SimpleGrantedAuthority(ele.getAuthorityName()))
															.collect(Collectors.toSet());
			
			return new User(user.getEmail(), user.getPassword(),
							user.isEnabled(), user.isAccountNonExpired(),
							user.isCredentialsNonExpired(),
							user.isAccountNonLocked(), authorities);
			
		}
		
		throw new UsernameNotFoundException(String.format("username %s is not valid",username).toString());
	}
	
	

}
