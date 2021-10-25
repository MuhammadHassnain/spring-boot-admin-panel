package com.adminpanel.security.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Authority {
	

	@Id
	@Column(name = "authority_id", nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorityId;

	@Column(name = "authority_name", nullable = false , unique = true)
	private String authorityName;
	
	
	
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_type_id" , referencedColumnName = "user_type_id")
	private UserType userType;



	public Authority(String authorityName, UserType userType) {
		this.authorityName = authorityName;
		this.userType = userType;
	}

	
	
}
