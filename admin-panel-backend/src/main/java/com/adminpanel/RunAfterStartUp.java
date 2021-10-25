package com.adminpanel;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.adminpanel.security.user.entity.Authority;
import com.adminpanel.security.user.entity.User;
import com.adminpanel.security.user.entity.UserType;
import com.adminpanel.security.user.service.AuthorityService;
import com.adminpanel.security.user.service.UserService;
import com.adminpanel.security.user.service.UserTypeService;

@Component
public class RunAfterStartUp {
	
	
	private final UserService userService;
	private final UserTypeService userTypeService;
	private final AuthorityService authorityService;
	private final PasswordEncoder passwordEncoder;
	
	
	

	
	
	@Autowired
	public RunAfterStartUp(UserService userService, UserTypeService userTypeService,
			AuthorityService authorityService, PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.userTypeService = userTypeService;
		this.authorityService = authorityService;
		this.passwordEncoder = passwordEncoder;
	}







	@EventListener(ApplicationReadyEvent.class)
	public void start() {
		
		UserType adminType = new UserType("ADMIN");
		UserType personType = new UserType("PERSON");
		
		adminType = userTypeService.save(adminType);
		personType = userTypeService.save(personType);
		
		Authority authority = new Authority("read:admin", adminType);
		Authority authority1 = new Authority("write:admin", adminType);
		
		authority = authorityService.save(authority);
		authority1 = authorityService.save(authority1);
		
		Set<Authority> authorities  = Set.of(authority,authority1);
		
		User user = new User("test@test.com", passwordEncoder.encode("password"), authorities, true, true, true, true, adminType);
		
		userService.save(user);
		
		User user1 = userService.findUserByEmail("test@test.com");
		
		System.out.println(user1);
		
	}
}
