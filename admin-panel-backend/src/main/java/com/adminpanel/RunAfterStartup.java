package com.adminpanel;

import java.util.HashSet;

import com.adminpanel.security.user.entity.Authority;
import com.adminpanel.security.user.entity.User;
import com.adminpanel.security.user.entity.UserType;
import com.adminpanel.security.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class RunAfterStartup {

    UserService userService;

    @Autowired
    public RunAfterStartup(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void appReadyEvent() {

        User user = new User("email", "password", new HashSet<Authority>(), true, true, true, true,
                new UserType("ADMIN"));

        userService.saveUser(user);

    }
}
