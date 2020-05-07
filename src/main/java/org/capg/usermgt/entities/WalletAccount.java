package org.capg.usermgt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*@Entity
@Table(name = "accounts")
public class WalletAccount {
  @Id
  @GeneratedValue
  private int accountId;
  private double accountBalance;

public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public double getAccountBalance() {
	return accountBalance;
}
public void setAccountBalance(double accountBalance) {
	this.accountBalance = accountBalance;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(accountBalance);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + accountId;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	WalletAccount other = (WalletAccount) obj;
	if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
		return false;
	if (accountId != other.accountId)
		return false;
	return true;
}
  
}*/
