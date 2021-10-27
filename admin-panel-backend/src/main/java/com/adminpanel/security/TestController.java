package com.adminpanel.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	
	@RequestMapping("/")
	public String test() {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		return user;
	}
}
