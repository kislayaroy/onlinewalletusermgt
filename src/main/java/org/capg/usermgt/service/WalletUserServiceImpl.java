package org.capg.usermgt.service;

import java.util.List;
import java.util.Optional;

import org.capg.usermgt.dao.WalletUserDao;
import org.capg.usermgt.entities.WalletAccount;
import org.capg.usermgt.entities.WalletUser;
import org.capg.usermgt.exception.UserIdNotFoundException;
import org.capg.usermgt.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletUserServiceImpl implements IWalletUserService {

	@Autowired
	private WalletUserDao userdao;
	@Autowired
	private WalletAccountServiceImpl accountService;
	
	@Override
	public List<WalletUser> getAllUsers() {
		List<WalletUser> users =userdao.findAll();
		return users;
	}

	@Override
	public WalletUser createUser(WalletUser user) {
	    user =userdao.save(user);
		addAccount(user.getUserId());
		return user;
	}

	@Override
	public WalletUser findById(int userId) {
		Optional<WalletUser> optional=userdao.findById(userId);
		if(optional.isPresent()) {
			WalletUser user=optional.get();
			return user;
		}
			throw new UserNotFoundException("User not found for id= "+userId);
        }

	@Override
	public WalletUser updateUser(WalletUser user) {
		Optional<WalletUser> optional = userdao.findById(user.getUserId());
		if(optional.isPresent()) {
			userdao.save(user);
			return user;
		}
			throw new UserIdNotFoundException("User id not found ="+user.getUserId());
	}

	@Override
	public boolean deleteUser(int userId) {
		Optional<WalletUser> optional=userdao.findById(userId);
		if(optional.isPresent()) {
			userdao.delete(optional.get());
		}
		else {
			throw new UserNotFoundException("User not found for id= "+userId);
		}
		return true;
		
	}

	@Override
	public WalletAccount addAccount(int userId) {
		WalletUser user = userdao.findById(userId).get();
		WalletAccount account = accountService.addAccount(userId);
		user.setAccount(account);
		userdao.save(user);
		return account;
	}
}
