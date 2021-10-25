package com.adminpanel.security.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.adminpanel.security.user.entity.Authority;
import com.adminpanel.security.user.repository.AuthorityRepository;
import com.adminpanel.security.user.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepo;

	
	
	@Override
	public Authority findById(Integer Id) {
		Optional<Authority> authority = authorityRepo.findById(Id);
		
		if(authority.isPresent()) {
			return authority.get();
		}
		return null;
	}

	@Override
	public Authority save(Authority obj) {
		return authorityRepo.save(obj);
	}


}
