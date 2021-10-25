package com.adminpanel.security.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.adminpanel.security.user.entity.UserType;
import com.adminpanel.security.user.repository.UserTypeRepository;
import com.adminpanel.security.user.service.UserTypeService;

@Service
public class UserTypeSericeImpl implements UserTypeService {

	@Autowired
	private UserTypeRepository userTypeRepo;

	@Override
	public UserType findById(Integer Id) {
		Optional<UserType> userOptional = userTypeRepo.findById(Id);
		
		if(userOptional.isPresent()) {
			return userOptional.get();
		}
		return null;
	}

	@Override
	public UserType save(UserType userType) {
		return userTypeRepo.save(userType);
	}
	
	


}
