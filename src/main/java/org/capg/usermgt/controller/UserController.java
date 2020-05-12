package org.capg.usermgt.controller;

import java.util.List;

import org.capg.usermgt.dto.WalletUserDto;
import org.capg.usermgt.entities.WalletAccount;
import org.capg.usermgt.entities.WalletUser;
import org.capg.usermgt.service.IWalletAccountService;
import org.capg.usermgt.service.IWalletUserService;
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

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IWalletUserService userservice;
	@Autowired
	private IWalletAccountService accountService;
	
	  public WalletUser convert(WalletUserDto dto) {
			WalletUser walletUser = new WalletUser();
			walletUser.setUserId(dto.getUserId());
			walletUser.setLoginName(dto.getLoginName());
		    walletUser.setPassword(dto.getPassword());
	        walletUser.setUserName(dto.getUserName());
            walletUser.setPhoneNumber(dto.getPhoneNumber());
            return walletUser;
		}
	
	  public WalletUserDto convertToDto(WalletUser walletUser) {
			WalletUserDto dto=new WalletUserDto();
			dto.setUserId(walletUser.getUserId());
			dto.setLoginName(walletUser.getLoginName());
			dto.setPassword(walletUser.getPassword());
			dto.setUserName(walletUser.getUserName());
			dto.setPhoneNumber(walletUser.getPhoneNumber());
			return dto;
		}
	//get all user api
	@GetMapping()
	public ResponseEntity<List<WalletUser>> getAllUsers(){
		List<WalletUser> userList=userservice.getAllUsers();
	    ResponseEntity<List<WalletUser>> response= new ResponseEntity<>(userList, HttpStatus.OK);
	    return response;
	}
	
	//create user
	@PostMapping("/add")
	public ResponseEntity<WalletUser> createUser(@RequestBody WalletUserDto dto) {
		WalletUser user=convert(dto);
		user=userservice.createUser(user);
		ResponseEntity<WalletUser> response = new ResponseEntity<>(user,HttpStatus.OK);
		return response;
	}
	@PostMapping("/{id}/account/add")
	public ResponseEntity<WalletAccount> addAccount(@PathVariable("id") int userId){
		WalletAccount account= userservice.addAccount(userId);
		ResponseEntity<WalletAccount> response = new ResponseEntity<WalletAccount>(account,HttpStatus.OK);
		return response;
	}
	//get user by id
	@GetMapping("/get/{id}")
	public ResponseEntity<WalletUserDto> getUserById(@PathVariable(value = "id") int userId){
		WalletUser user=userservice.findById(userId);
		WalletUserDto dto=convertToDto(user);
		ResponseEntity<WalletUserDto> response =new ResponseEntity<>(dto,HttpStatus.OK);
	    return response;
		}
	
	@GetMapping("/get/{id}/account")
	public ResponseEntity<WalletAccount> getAccount(@PathVariable("id") int accountId){
		WalletAccount account =accountService.getAccount(accountId);
		ResponseEntity<WalletAccount> response = new ResponseEntity<WalletAccount>(account,HttpStatus.OK);
		return response;
	}
	//update user
	@PutMapping("/get/{id}")
	public ResponseEntity<WalletUserDto> updateUser(@PathVariable(value = "id") int userId,@RequestBody WalletUserDto dto){
		WalletUser walletUser=userservice.findById(userId);
		walletUser.setUserId(walletUser.getUserId());
		walletUser.setLoginName(dto.getLoginName());
		walletUser.setPassword(dto.getPassword());
		walletUser.setUserName(dto.getUserName());
		walletUser.setPhoneNumber(dto.getPhoneNumber());
	    walletUser=userservice.updateUser(walletUser);
		WalletUserDto walletUserDetails=convertToDto(walletUser);
		ResponseEntity<WalletUserDto> response=new ResponseEntity<>(walletUserDetails,HttpStatus.OK);
			return response;
			}
	//deleted user by id
	@DeleteMapping("/get/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable int userId){
		boolean result=	userservice.deleteUser(userId);
		ResponseEntity<Boolean>response=new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleAll(Throwable e){
		log.error("exception caught", e);
		String msg = e.getMessage();
		ResponseEntity<String>response = new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
}
