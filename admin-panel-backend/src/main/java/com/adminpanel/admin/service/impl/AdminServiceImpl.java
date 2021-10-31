package com.adminpanel.admin.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminpanel.admin.entity.Admin;
import com.adminpanel.admin.repository.AdminRepository;
import com.adminpanel.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	
	
	@Autowired AdminRepository adminRepo;
	

	@Override
	public Admin createAdmin(Admin admin) {
		
		return adminRepo.save(admin);
	}


	@Override
	public Admin findAdminById(Integer id) {
		Optional<Admin> findById = adminRepo.findById(id);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
}
