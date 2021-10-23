package com.adminpanel.security.user.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@Column(name = "user_type_id", nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userTypeId;

	@Column(name = "user_type_name", nullable = false , unique = true)
	private String userTypeName;
	

}
