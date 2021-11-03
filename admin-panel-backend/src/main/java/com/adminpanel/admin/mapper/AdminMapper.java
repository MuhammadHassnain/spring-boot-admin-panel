package com.adminpanel.admin.mapper;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adminpanel.admin.dto.AdminDTO;
import com.adminpanel.admin.entity.Admin;

@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AdminMapper {
	
	public AdminDTO adminToAdminDTO(Admin admin) {
		
		if(admin == null) {
			return null;
		}
		
		AdminDTO dto = new AdminDTO();
		
		dto.setAdminId(admin.getAdminId());
		dto.setFirstName(admin.getFirstName());
		dto.setLastName(admin.getLastName());
		dto.setSecondaryEmail(admin.getSecondaryEmail());
		
		return dto;
	}


}
