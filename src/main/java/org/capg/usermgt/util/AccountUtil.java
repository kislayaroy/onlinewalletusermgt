package org.capg.usermgt.util;

import org.capg.usermgt.dto.CreateUserRequest;
import org.capg.usermgt.dto.UpdateUserRequest;
import org.capg.usermgt.dto.UserDetails;
import org.capg.usermgt.entities.WalletUser;

  public class AccountUtil {
	
	  public static WalletUser convertToUser(CreateUserRequest request) {
		WalletUser walletUser = new WalletUser();
		walletUser.setUserId(request.getUserId());
		walletUser.setLoginName(request.getLoginName());
	    walletUser.setPassword(request.getPassword());
        walletUser.setUserName(request.getUserName());
        walletUser.setPhoneNumber(request.getPhoneNumber());
        return walletUser;
	}
	  
	  
	  public static void setDetails(WalletUser user, UpdateUserRequest requestData) {
		  user.setUserName(requestData.getUserName());
		  user.setPassword(requestData.getPassword());
		  user.setLoginName(requestData.getLoginName());
		  user.setPhoneNumber(requestData.getPhoneNumber());
	  }
	  
	  
	  public static UserDetails convertFromUser(WalletUser user) {
		  UserDetails userDetails = new UserDetails();
		  return userDetails;
	  }
}
