package org.capg.usermgt.service;

import java.util.List;

import org.capg.usermgt.entities.WalletAccount;
import org.capg.usermgt.entities.WalletUser;

public interface IWalletUserService {

    List<WalletUser> getAllUsers();

    WalletUser createUser(WalletUser user);

    WalletUser findById(int userId);

    WalletUser updateUser(WalletUser user);

    void deleteUser(int userId);

    WalletAccount findAccountByUserId(int userId);

    WalletUser debit(int userId, double amount);

    WalletUser credit(int userId, double amount);
}
