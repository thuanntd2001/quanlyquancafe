package com.quancafehighland.model;

import spring.entity.UserTBEntity;


public class UserModel extends UserTBEntity{

	
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long iD;
	
	private Long roleID;

	public Long getiD() {
		return iD;
	}

	public void setiD(Long iD) {
		this.iD = iD;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}


	
	
}
