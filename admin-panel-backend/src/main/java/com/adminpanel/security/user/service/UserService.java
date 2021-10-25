package com.adminpanel.security.user.service;

import com.adminpanel.security.user.entity.User;

public interface UserService extends CrudOperation<User, Integer>{
	
	 User findUserByEmail(String email);
	 

}
