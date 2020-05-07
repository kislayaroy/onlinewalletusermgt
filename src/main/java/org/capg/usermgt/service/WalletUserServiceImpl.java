package org.capg.usermgt.service;

import java.util.List;
import java.util.Optional;

import org.capg.usermgt.dao.WalletUserDao;
import org.capg.usermgt.entities.WalletUser;
import org.capg.usermgt.exception.UserIdNotFoundException;
import org.capg.usermgt.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletUserServiceImpl implements IWallerUserService {

	@Autowired
	private WalletUserDao userdao;
	
	@Override
	public List<WalletUser> getAllUsers() {
		return userdao.findAll();
	}

	@Override
	public WalletUser createUser(WalletUser user) {
		return userdao.save(user);
	}

	@Override
	public WalletUser findById(int userId) {
		Optional<WalletUser> optional=userdao.findById(userId);
		if(optional.isPresent()) {
			WalletUser user=optional.get();
			return user;
		}
		else {
			throw new UserNotFoundException("User not found for id= "+userId);
        }
        }

	@Override
	public WalletUser updateUser(WalletUser user) {
		Optional<WalletUser> optional = userdao.findById(user.getUserId());
		if(optional.isPresent()) {
			WalletUser userUpdate= optional.get();
			userUpdate.setUserId(user.getUserId());
			userUpdate.setUserName(user.getUserName());
			userUpdate.setPassword(user.getPassword());
			userUpdate.setPhoneNumber(user.getPhoneNumber());
			userdao.save(userUpdate);
			return userUpdate;
		}
		else {
			throw new UserIdNotFoundException("User id not found ="+user.getUserId());
		}
	}

	@Override
	public void deleteUser(int userId) {
		Optional<WalletUser> optional=userdao.findById(userId);
		if(optional.isPresent()) {
			userdao.delete(optional.get());
		}
		else {
			throw new UserNotFoundException("User not found for id= "+userId);
		}
		
	}
}
