package org.capg.usermgt.dao;

import org.capg.usermgt.entities.WalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletAccountDao extends JpaRepository<WalletAccount,Integer> {

}
