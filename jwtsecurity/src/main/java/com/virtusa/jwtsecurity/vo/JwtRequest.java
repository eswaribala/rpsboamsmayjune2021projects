package com.virtusa.jwtsecurity.vo;

//DTO
public class JwtRequest {

	private String userName;
	private String userPwd;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserpwd(String userpwd) {
		this.userPwd = userpwd;
	}

	
}
