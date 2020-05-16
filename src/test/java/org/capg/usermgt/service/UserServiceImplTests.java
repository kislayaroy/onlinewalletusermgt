package org.capg.usermgt.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.capg.usermgt.entities.WalletUser;
import org.capg.usermgt.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest // for jpa tests
@ExtendWith(SpringExtension.class) // integrate spring test framework with junit5
@Import(WalletUserServiceImpl.class)
public class UserServiceImplTests {

    @Autowired
    private IWalletUserService walletService;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void testCreateUser_1() {
        int id = 1, accId = 1234;
        WalletUser user = new WalletUser();
        user.setUserId(id);
        WalletUser result = walletService.createUser(user);
        List<WalletUser> fetched = entityManager.createQuery("user").getResultList();
        Assertions.assertEquals(1, fetched.size());
        WalletUser expected = fetched.get(0);
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(id, expected.getUserId());
    }

    @Test
    public void testCreateUser_2() {
        int id = 2, accId = 4321;
        WalletUser user = new WalletUser();
        user.setUserId(id);
        user = entityManager.merge(user);
        int newId = 2, newAccId = 4321;
        boolean newStatus = false;
        user.setUserId(newId);
        WalletUser result = walletService.createUser(user);
        List<WalletUser> fetched = entityManager.createQuery("user").getResultList();
        Assertions.assertEquals(1, fetched.size());
        WalletUser expected = fetched.get(0);
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(newId, expected.getUserId());
    }

    @Test
    public void testFindByUserId_1() {
        Executable executable = () -> walletService.findById(1234);
        Assertions.assertThrows(UserNotFoundException.class, executable);

    }

    @Test
    public void testFindByUserId_2() {
        int id = 1, accId = 123;
        WalletUser user = new WalletUser();
        user.setUserId(id);
        user = entityManager.merge(user);
        int userId = user.getUserId();
        WalletUser result = walletService.findById(id);
        //
        //verifying details are correctly fetched
        //
        Assertions.assertEquals(user, result);
        Assertions.assertEquals(id, user.getUserId());
    }
}
