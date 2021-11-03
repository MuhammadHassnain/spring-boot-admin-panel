package com.adminpanel.security.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adminpanel.admin.dto.AdminDTO;
import com.adminpanel.admin.entity.Admin;
import com.adminpanel.admin.mapper.AdminMapper;
import com.adminpanel.security.user.dto.UserDTO;
import com.adminpanel.security.user.entity.User;
import com.adminpanel.security.user.mapper.UserMapper;

@Component
public class ResponseMapper {
	
	@Autowired private AdminMapper adminMapper;
	
	@Autowired private UserMapper userMapper;
	
	
	public AdminDTO adminToAdminDTO(Admin admin) {
		 return adminMapper.adminToAdminDTO(admin);
	}
	
	public UserDTO userToUserDTO(User user){
		return userMapper.UserToUserDTO(user);
	}
	
	
}
