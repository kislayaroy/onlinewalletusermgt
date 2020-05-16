package org.capg.usermgt.util;

import org.capg.usermgt.dto.UserDetails;
import org.capg.usermgt.entities.WalletAccount;
import org.capg.usermgt.entities.WalletUser;

import java.util.Map;

public class AccountUtil {

    public static void setRequestData(WalletUser walletUser, Map<String, Object> requestData) {
        String loginName = (String) requestData.get("loginName");
        walletUser.setLoginName(loginName);
        String userName = (String) requestData.get("userName");
        String password = (String) requestData.get("password");
        walletUser.setPassword(password);
        walletUser.setUserName(userName);
        String phoneNumber = (String) requestData.get("phoneNumber");
        walletUser.setPhoneNumber(phoneNumber);
    }


    public static UserDetails userDetails(WalletUser user) {
        WalletAccount account = user.getAccount();
        UserDetails userDetails = new UserDetails();
        userDetails.setAccountId(account.getAccountId());
        userDetails.setUserId(user.getUserId());
        userDetails.setAccountBalance(account.getAccountBalance());
        userDetails.setLoginName(user.getLoginName());
        userDetails.setUserName(user.getUserName());
        userDetails.setPhoneNumber(user.getPhoneNumber());
        userDetails.setPassword(user.getPassword());
        userDetails.setStatus(account.getStatus());
        return userDetails;
    }
}
