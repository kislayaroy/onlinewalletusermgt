package org.capg.usermgt.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name= "walletusers")
public class WalletUser {
	
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	private String password;
	private String phoneNumber;
	private String loginName;
	WalletAccount account;
	
	public WalletUser() {
		super();
	}
	public WalletUser(int userId, String userName, String password, String phoneNumber, String loginName,
			WalletAccount account) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.loginName = loginName;
		this.account = account;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
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
	public WalletAccount getAccount() {
		return account;
	}
	public void setAccount(WalletAccount account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "User [userId= " + userId +" userName= " +userName + " password= " + password + " phoneNumber= "+ phoneNumber +" loginName= "+ loginName + " Account= " + account + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null||getClass() != obj.getClass())
			return false;
		WalletUser other = (WalletUser) obj;
		return userId == other.userId;
	}
	
}
