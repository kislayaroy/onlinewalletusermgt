package org.capg.usermgt.dto;

import org.capg.usermgt.util.Status;

public class WalletAccountDto {
     int accountId;
     int accountBalance;
     Status status;
	
     
     public WalletAccountDto() {
		super();
	}
	public WalletAccountDto(int accountId, int accountBalance, Status status) {
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.status = status;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "WalletAccountDto [accountId=" + accountId + ", accountBalance=" + accountBalance + ", status=" + status
				+ "]";
	}
     
     
}
