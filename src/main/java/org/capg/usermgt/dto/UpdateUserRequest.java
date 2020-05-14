package org.capg.usermgt.dto;

public class UpdateUserRequest {
	private String userName;
	private String password;
	private String phoneNumber;
	private String loginName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	@Override
	public String toString() {
		return "UpdateUserRequest [userName=" + userName + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", loginName=" + loginName + "]";
	}
	

}
