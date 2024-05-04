package com.h3m.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h3m.User.DTO.LoginDTO;
import com.h3m.User.DTO.UserDTO;
import com.h3m.User.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
	UserService userSer;
    
    @PostMapping("/create")
    ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
    	userSer.createUser(userDTO);
    	return new ResponseEntity<>("User Created Successfully",HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    ResponseEntity<String> userLogin(@RequestBody LoginDTO loginDTO){
    	String msg=userSer.userLogin(loginDTO);
    	return new ResponseEntity<>(msg,HttpStatus.OK);
    }
    
    @PutMapping("/updateUserRole/{userName}/{role}")
    ResponseEntity<String> updateUserRole(@PathVariable("userName") String userName,@PathVariable("role") String role){
    	String msg=userSer.updateUserRole(userName,role);
    	return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }
    
    
}
