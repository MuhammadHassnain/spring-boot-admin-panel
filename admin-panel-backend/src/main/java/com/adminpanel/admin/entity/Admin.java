package com.adminpanel.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	
	
	
	@Column(name = "admin_id",nullable = false,unique=true)
	@Id
	Integer adminId;
	@Column(name="admin_first_name",nullable = false,length = 50)
	String firstName;
	@Column(name="admin_last_name",nullable = false,length = 50)
	String lastName;
	
	@Column(name="admin_secondary_email", length = 60)
	String secondaryEmail;
	
	
}
