package com.adminpanel.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.adminpanel.security.user.service.ApplicationUserDetailService;

@EnableWebSecurity
@Configuration
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserDetailService userDetialService;
	
	
	
	
	@Autowired
	public ApplicationConfiguration(PasswordEncoder passwordEncoder,ApplicationUserDetailService userDetialService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDetialService = userDetialService;
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			csrf().disable()
			.formLogin()
			.usernameParameter("username").passwordParameter("password")
			.and()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.anyRequest().authenticated();

	}

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {


		auth.userDetailsService(userDetialService).passwordEncoder(passwordEncoder);
	}
	
	
	
	
}
