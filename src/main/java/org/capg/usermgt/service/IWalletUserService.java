package org.capg.usermgt.service;

import java.util.List;

import org.capg.usermgt.entities.WalletUser;

public interface IWalletUserService {

	 List<WalletUser> getAllUsers();
	
	 WalletUser createUser(WalletUser user);
	 
	 WalletUser findById(int userId);
	 
	 WalletUser updateUser(WalletUser user);
	 
	 boolean deleteUser(int userId);
}
