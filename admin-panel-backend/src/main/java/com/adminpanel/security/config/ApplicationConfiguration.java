package com.adminpanel.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.adminpanel.exception.ExceptionHandlerFilter;
import com.adminpanel.security.jwt.JwtManager;
import com.adminpanel.security.jwt.filter.CustomUsernamePasswordAuthentication;
import com.adminpanel.security.jwt.filter.UsernamePasswordAuthFilter;
import com.adminpanel.security.user.service.ApplicationUserDetailService;

@EnableWebSecurity
@Configuration
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserDetailService userDetialService;
	private final JwtManager jwtManager;
	
	
	
	
	
	@Autowired
	public ApplicationConfiguration(PasswordEncoder passwordEncoder,ApplicationUserDetailService userDetialService, JwtManager jwtManager) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDetialService = userDetialService;
		this.jwtManager = jwtManager;
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.formLogin()
			.usernameParameter("username").passwordParameter("password")
			.and()
			.authorizeRequests()
			.antMatchers("/login","/").permitAll()
			.anyRequest().authenticated();
		
		
		http.addFilterBefore(new ExceptionHandlerFilter(),CustomUsernamePasswordAuthentication.class);
		http.addFilter(new CustomUsernamePasswordAuthentication(jwtManager,authenticationManager()));
		http.addFilterAfter(new UsernamePasswordAuthFilter(jwtManager), CustomUsernamePasswordAuthentication.class);

	}

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {


		auth.userDetailsService(userDetialService).passwordEncoder(passwordEncoder);
	}
	
	
	
	
}
