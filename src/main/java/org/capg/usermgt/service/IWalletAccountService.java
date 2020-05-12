package org.capg.usermgt.service;

import java.util.List;

import org.capg.usermgt.entities.WalletAccount;

public interface IWalletAccountService {
   WalletAccount addAccount(int accountId);
   
   WalletAccount getAccount(int accountId);
   
   List<WalletAccount> getAllAccounts();
   
}
