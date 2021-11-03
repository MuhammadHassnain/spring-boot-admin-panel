package com.adminpanel.security.user.mapper;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adminpanel.security.user.dto.AuthorityDTO;
import com.adminpanel.security.user.entity.Authority;

@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AuthorityMapper {
	
	
	public AuthorityDTO authorityToAuthorityDTO(Authority authority) {
		
		
		AuthorityDTO dto = new AuthorityDTO();
		dto.setAuthorityId(authority.getAuthorityId());
		dto.setAuthorityName(authority.getAuthorityName());
		dto.setUserType(authority.getUserType().getUserTypeName());
		return dto;
	}
	
	
	public AuthorityDTO authorityToAuthorityDTO(Authority authority,boolean includeType) {
		
		
		AuthorityDTO dto = new AuthorityDTO();
		dto.setAuthorityId(authority.getAuthorityId());
		dto.setAuthorityName(authority.getAuthorityName());
		if(includeType) {
			dto.setUserType(authority.getUserType().getUserTypeName());
		}
		return dto;
	}
}
