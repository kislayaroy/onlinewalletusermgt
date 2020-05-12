package org.capg.usermgt.service;

import java.util.List;

import org.capg.usermgt.dao.WalletAccountDao;
import org.capg.usermgt.entities.WalletAccount;
import org.capg.usermgt.exception.AccountAlreadyExistsException;
import org.capg.usermgt.exception.AccountDoesNotExistsException;
import org.capg.usermgt.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletAccountServiceImpl implements IWalletAccountService {

	@Autowired
	WalletAccountDao accountdao;
	
	@Override
	public WalletAccount addAccount(int accountId) {
		boolean exists= accountdao.existsById(accountId);
		if(exists) {
			throw new AccountAlreadyExistsException("User with the account id already exists = " +accountId);
		}
		WalletAccount account = new WalletAccount();
		account.setAccountId(accountId);
		account.setAccountBalance(account.getAccountBalance());
		account.setStatus(Status.TRUE);
		WalletAccount acc= accountdao.save(account);
		return acc;
	}

	@Override
	public WalletAccount getAccount(int accountId) {
		boolean exists = accountdao.existsById(accountId);
		if(exists) {
			 WalletAccount account = accountdao.getOne(accountId);
			 return account;			
			}
		throw new AccountDoesNotExistsException("User with account id does not exists = "+accountId);
	}

	@Override
	public List<WalletAccount> getAllAccounts() {
		List<WalletAccount> account = accountdao.findAll();
		return account;
	}
}
