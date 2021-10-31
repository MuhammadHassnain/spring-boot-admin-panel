package com.adminpanel.admin.service;

import com.adminpanel.admin.entity.Admin;

public interface AdminService {
	
	Admin createAdmin(Admin admin);

	Admin findAdminById(Integer id);
}
