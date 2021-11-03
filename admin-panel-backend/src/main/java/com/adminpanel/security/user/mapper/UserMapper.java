package com.adminpanel.security.user.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adminpanel.security.user.dto.AuthorityDTO;
import com.adminpanel.security.user.dto.UserDTO;
import com.adminpanel.security.user.entity.User;


@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserMapper {
	
	@Autowired private AuthorityMapper authorityMapper;
	
	public UserDTO UserToUserDTO(User user) {
		
		UserDTO dto =new UserDTO();
		
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setUserType(user.getUserType().getUserTypeName());
		
		
		Set<AuthorityDTO> authorityDTOs = user.getAuthorities()
										.stream()
										.map(obj -> authorityMapper.authorityToAuthorityDTO(obj,false) )
										.collect(Collectors.toSet());
		dto.setAuthorities(authorityDTOs);
		return dto;

	}

}
