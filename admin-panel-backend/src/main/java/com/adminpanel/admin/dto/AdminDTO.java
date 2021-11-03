package com.adminpanel.admin.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
	
	@JsonProperty("id")
	Integer adminId;
	
	@JsonProperty("firstName")
	@NotNull
	String firstName;
	@JsonProperty("lastName")
	@NotNull
	String lastName;
	
	@JsonProperty("secondaryEmail")
	@Email
	String secondaryEmail;
}
