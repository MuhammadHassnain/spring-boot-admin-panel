package com.adminpanel.security.user.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class UserDTO {
	
	@JsonProperty("userId")
	private Integer id;

	@JsonProperty("primaryEmail")
	@Email
	@NotNull
	private String email;
	
	@JsonProperty("authorities")
	private Set<AuthorityDTO> authorities;

	@JsonProperty("userType")
	private String userType;
}
