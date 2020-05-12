package org.capg.usermgt.dao;

import java.util.List;

import org.capg.usermgt.entities.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletUserDao extends JpaRepository<WalletUser,Integer> {
}
