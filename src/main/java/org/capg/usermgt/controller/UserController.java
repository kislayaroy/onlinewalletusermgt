package org.capg.usermgt.controller;

import java.util.List;
import java.util.Map;

import org.capg.usermgt.dto.TransactionDetails;
import org.capg.usermgt.dto.UserDetails;
import org.capg.usermgt.entities.WalletUser;
import org.capg.usermgt.exception.InsufficientBalanceException;
import org.capg.usermgt.exception.UserNotFoundException;
import org.capg.usermgt.service.IWalletUserService;
import org.capg.usermgt.util.AccountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger Log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IWalletUserService userService;

    //get all user api
    @GetMapping
    public ResponseEntity<List<WalletUser>> getAllUsers() {
        List<WalletUser> userList = userService.getAllUsers();
        ResponseEntity<List<WalletUser>> response = new ResponseEntity<>(userList, HttpStatus.OK);
        return response;
    }

    //create user
    @PostMapping("/add")
    public ResponseEntity<UserDetails> createUser(@RequestBody Map<String, Object> requestData) {
        WalletUser user = new WalletUser();
        AccountUtil.setRequestData(user, requestData);
        user = userService.createUser(user);
        UserDetails userDetails = AccountUtil.userDetails(user);
        ResponseEntity<UserDetails> response = new ResponseEntity<>(userDetails, HttpStatus.OK);
        return response;
    }

    //get user by id
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDetails> getUserById(@PathVariable("id") int userId) {
        WalletUser user = userService.findById(userId);
        UserDetails userDetails = AccountUtil.userDetails(user);
        ResponseEntity<UserDetails> response = new ResponseEntity<>(userDetails, HttpStatus.OK);
        return response;
    }

    //update user
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDetails> updateUser(@PathVariable("id") int userId,
                                                  @RequestBody Map<String, Object> requestData) {
        WalletUser user = userService.findById(userId);
        AccountUtil.setRequestData(user, requestData);
        user = userService.updateUser(user);
        UserDetails userDetails = AccountUtil.userDetails(user);
        ResponseEntity<UserDetails> response = new ResponseEntity<>(userDetails, HttpStatus.OK);
        return response;
    }

    //deleted user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        ResponseEntity<Boolean> response = new ResponseEntity<>(true, HttpStatus.OK);
        return response;
    }

    @PutMapping("/{userId}/credit")
    public ResponseEntity<UserDetails> credit(@RequestBody Map<String, Object> requestData) {
        int userId = (int) requestData.get("userId");
        String amountText=requestData.get("amount").toString();
        Double amount = Double.parseDouble(amountText);
        WalletUser user = userService.credit(userId, amount);
        UserDetails userDetails = AccountUtil.userDetails(user);
        TransactionDetails transactionDetails=new TransactionDetails();
        transactionDetails.setAmount(amount);
        transactionDetails.setUserId(userId);
        transactionDetails.setTransactionType("credit");
        sendTransactionDetails(transactionDetails);
        ResponseEntity<UserDetails> response = new ResponseEntity<>(userDetails, HttpStatus.OK);
        return response;
    }

    @PutMapping("/{userId}/debit")
    public ResponseEntity<UserDetails> debit(@RequestBody Map<String, Object> requestData) {
        int userId = (int) requestData.get("userId");
        String amountText=requestData.get("amount").toString();
        Double amount = Double.parseDouble(amountText);
        WalletUser user = userService.debit(userId, amount);
        UserDetails userDetails = AccountUtil.userDetails(user);
        TransactionDetails transactionDetails=new TransactionDetails();
        transactionDetails.setAmount(amount);
        transactionDetails.setUserId(userId);
        transactionDetails.setTransactionType("debit");
        sendTransactionDetails(transactionDetails);

        ResponseEntity<UserDetails> response = new ResponseEntity<>(userDetails, HttpStatus.OK);
        return response;
    }

    /**
     *
     * sends Transaction details to transaction service after integration
     */
    public void sendTransactionDetails(TransactionDetails details){

    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String>handleInsufficientBalance(InsufficientBalanceException ex){
       String msg=ex.getMessage();
       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
       return response;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable e) {
        Log.error("exception caught", e);
        String msg = e.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
