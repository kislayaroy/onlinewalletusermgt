package org.capg.usermgt.controller;

import java.util.List;

import org.capg.usermgt.dto.CreateUserRequest;
import org.capg.usermgt.dto.UpdateUserRequest;
import org.capg.usermgt.dto.UserDetails;
import org.capg.usermgt.entities.WalletAccount;
import org.capg.usermgt.entities.WalletUser;
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
	private IWalletUserService userservice;
	//get all user api
	@GetMapping()
	public ResponseEntity<List<WalletUser>> getAllUsers(){
		List<WalletUser> userList=userservice.getAllUsers();
	    ResponseEntity<List<WalletUser>> response= new ResponseEntity<>(userList, HttpStatus.OK);
	    return response;
	}
	
	//create user
	@PostMapping("/add")
	public ResponseEntity<UserDetails> createUser(@RequestBody CreateUserRequest requestData) {
		WalletUser user=AccountUtil.convertToUser(requestData);
		user=userservice.createUser(user);
		UserDetails userDetails= AccountUtil.convertFromUser(user);
		ResponseEntity<UserDetails> response = new ResponseEntity<>(userDetails,HttpStatus.OK);
		return response;
	}
	//get user by id
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDetails> getUserById(@PathVariable("id") int userId){
		WalletUser user=userservice.findById(userId);
		UserDetails userDetails=AccountUtil.convertFromUser(user);
		ResponseEntity<UserDetails> response =new ResponseEntity<>(userDetails,HttpStatus.OK);
	    return response;
		}
	
	//update user
	@PutMapping("/get/{id}")
	public ResponseEntity<UserDetails> updateUser(@PathVariable("id") int userId,
			                                        @RequestBody UpdateUserRequest request){
		WalletUser user=userservice.findById(userId);
		AccountUtil.setDetails(user,request);
		user = userservice.updateUser(user);
		UserDetails userDetails=AccountUtil.convertToUser(user);
		ResponseEntity<UserDetails> response=new ResponseEntity<>(userDetails,HttpStatus.OK);
			return response;
			}
	//deleted user by id
	@DeleteMapping("/get/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable int userId){
		userservice.deleteUser(userId);
		ResponseEntity<Boolean>response=new ResponseEntity<>(true, HttpStatus.OK);
		return response;
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleAll(Throwable e){
		Log.error("exception caught", e);
		String msg = e.getMessage();
		ResponseEntity<String>response = new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
}
