package com.adminpanel.security.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.adminpanel.security.user.entity.User;
import com.adminpanel.security.user.repository.UserRepository;
import com.adminpanel.security.user.service.CrudOperation;
import com.adminpanel.security.user.service.UserService;




@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserByEmail(String email) {

		Optional<User> user = userRepository.findUserByEmail(email);

		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}


	@Override
	public User findById(Integer Id) {
		throw new UnsupportedOperationException("This service is not available yet");
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
