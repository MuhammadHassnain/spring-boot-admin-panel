package com.adminpanel.security.user.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	
	
	@Id
	@Column(name = "users_id",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="user_email",nullable=false,unique=true)
	private String email;
	
	@Column(name="user_password",nullable=false)
	private String password;
	
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "user_authorities", 
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="authority_id"))
	@Column(name="authority_id")
	private Set<Authority> authorities;
	
	@Column(name = "user_account_not_expired",nullable = false)
	private boolean accountNonExpired;

	@Column(name = "user_account_not_locked",nullable = false)
	private boolean accountNonLocked;

	@Column(name = "user_credentials_not_expired",nullable = false)
	private boolean credentialsNonExpired;

	@Column(name = "user_enabled",nullable = false)
	private boolean enabled;
	
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_type_id" , referencedColumnName = "user_type_id")
	private UserType userType;

	
	
}
