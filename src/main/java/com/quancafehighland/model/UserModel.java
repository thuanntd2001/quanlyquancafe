package com.quancafehighland.model;

public class UserModel {
	private Long iD;
	private String passwd;
	private Long roleID;
	private int status;
	private String userName;

	private String email;
	
	private String icon;

	public Long getiD() {
		return iD;
	}

	public void setiD(Long iD) {
		this.iD = iD;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


	
}
