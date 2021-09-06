package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="Role_Id")
	private long role_id;
	
	
	@Column(name="Role")
	private String role;


	public long getRole_id() {
		return role_id;
	}


	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
}
