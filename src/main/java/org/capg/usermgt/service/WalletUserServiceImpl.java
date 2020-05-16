package org.capg.usermgt.service;

import java.util.List;
import java.util.Optional;

import org.capg.usermgt.dao.WalletAccountDao;
import org.capg.usermgt.dao.WalletUserDao;
import org.capg.usermgt.entities.WalletAccount;
import org.capg.usermgt.entities.WalletUser;
import org.capg.usermgt.exception.InsufficientBalanceException;
import org.capg.usermgt.exception.UserIdNotFoundException;
import org.capg.usermgt.exception.UserNotFoundException;
import org.capg.usermgt.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletUserServiceImpl implements IWalletUserService {

	@Autowired
	private WalletUserDao userDao;
	@Autowired
	private WalletAccountDao accountDao;
	
	@Override
	public List<WalletUser> getAllUsers() {
		List<WalletUser> users =userDao.findAll();
		return users;
	}

	@Override
	public WalletUser createUser(WalletUser user) {
		WalletAccount account = new WalletAccount();
		account.setAccountBalance(0);
		account.setStatus(Status.ACTIVE);
		account = accountDao.save(account);
		//user.setAccount(account);
	    user =userDao.save(user);
		return user;
	}
	
	@Override
	public WalletAccount findAccountByUserId(int userId) {
		WalletUser user=findById(userId);
		WalletAccount account = user.getAccount();
		return account;
	}

	@Override
	public WalletUser findById(int userId) {
		Optional<WalletUser> optional=userDao.findById(userId);
		if(optional.isPresent()) {
			WalletUser user=optional.get();
			return user;
		}
			throw new UserNotFoundException("User not found for id= "+userId);
        }

	@Override
	public WalletUser updateUser(WalletUser user) {
		boolean exists = userDao.existsById(user.getUserId());
		if(exists) {
			userDao.save(user);
			return user;
		}
			throw new UserIdNotFoundException("User id not found ="+user.getUserId());
	}

	@Override
	public WalletUser credit(int userId, double amount){
		WalletUser user=findById(userId);
	    WalletAccount account=user.getAccount();
	    double previousBalance=account.getAccountBalance();
	    double newBalance=previousBalance+amount;
	    account.setAccountBalance(newBalance);
	    account=accountDao.save(account);
	    return user;
	}


	@Override
	public WalletUser debit(int userId, double amount){
		WalletUser user=findById(userId);
		WalletAccount account=user.getAccount();
		double previousBalance=account.getAccountBalance();
		if(previousBalance<amount){
			throw new InsufficientBalanceException("insufficient balance");
		}
		double newBalance=previousBalance-amount;
		account.setAccountBalance(newBalance);
		account=accountDao.save(account);
		return user;
	}

	@Override
	public void deleteUser(int userId) {
		WalletUser user = findById(userId);
		WalletAccount account= user.getAccount();
		account.setStatus(Status.INACTIVE);
	    accountDao.save(account);
	}
}
