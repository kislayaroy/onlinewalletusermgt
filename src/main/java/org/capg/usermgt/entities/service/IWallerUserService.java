package org.capg.usermgt.entities.service;

import java.util.List;

import org.capg.usermgt.entities.WalletUser;

public interface IWallerUserService {

	 List<WalletUser> getAllUsers();
	
	 WalletUser createUser(WalletUser user);
	 
	 WalletUser findById(int userId);
	 
	 WalletUser updateUser(WalletUser user);
	 
	 void deleteUser(int userId);
}
