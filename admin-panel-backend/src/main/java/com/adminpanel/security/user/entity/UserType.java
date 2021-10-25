package com.adminpanel.security.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_type")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserType {

	@Id
	@Column(name = "user_type_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userTypeId;

	@Column(name = "user_type_name", nullable = false, unique = true)
	private String userTypeName;

	public UserType(String userTypeName) {
		this.userTypeName = userTypeName;
	}

}
