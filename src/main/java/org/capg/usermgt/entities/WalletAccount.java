package org.capg.usermgt.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.capg.usermgt.util.Status;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "walletaccounts")
public class WalletAccount {
    @Id
    @GeneratedValue
    private int accountId;
    private double accountBalance;
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WalletAccount [accountId=" + accountId + ", accountBalance=" + accountBalance + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        WalletAccount other = (WalletAccount) obj;
        return accountId == other.accountId;
    }
}
