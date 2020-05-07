package org.capg.usermgt.controller;

import java.util.List;
import org.capg.usermgt.entities.WalletUser;
import org.capg.usermgt.service.IWallerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private IWallerUserService userservice;
	
	//get all user api
	@GetMapping("/users")
	public ResponseEntity<List<WalletUser>> getAllUsers(){
		return ResponseEntity.ok().body(userservice.getAllUsers());
	}
	
	//create user
	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody WalletUser user) {
		return ResponseEntity.ok().body(userservice.createUser(user));
	}
	
	//get user by id
	@GetMapping("users/{id}")
	public ResponseEntity<WalletUser> getUserById(@PathVariable(value = "id") int userId){
		return ResponseEntity.ok().body(userservice.findById(userId));
	}
	//update user
	@PutMapping("/users/{id}")
	public ResponseEntity<WalletUser> updateUser(@PathVariable(value = "id") int userId,@RequestBody WalletUser user){
	    user.setUserId(userId);
		return ResponseEntity.ok().body(userservice.updateUser(user));
	}
	//deleted user by id
	@DeleteMapping("/users/{id}")
	public HttpStatus deleteUser(@PathVariable int userId){
		userservice.deleteUser(userId);
		return HttpStatus.OK;
	}
}
