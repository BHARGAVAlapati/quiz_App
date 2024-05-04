package com.h3m.User.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h3m.User.DTO.LoginDTO;
import com.h3m.User.DTO.UserDTO;
import com.h3m.User.Entity.Users;
import com.h3m.User.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public void createUser(UserDTO userDTO) {
		
		int randomNumber = (int) (Math.random() * 1000);
	    String userName="HM"+String.valueOf(randomNumber);
	    Users user=new Users();
	    user.setUserId(userName);
	    user.setUserName(userDTO.getUserName());
	    user.setFirstName(userDTO.getFirstName());
	    user.setLastName(userDTO.getLastName());
	    user.setUserPassword(userDTO.getUserPassword());
	    user.setUserEmail(userDTO.getUserEmail());
	    user.setPhnoneNo(userDTO.getPhnoneNo());
	    user.setUserRole("user");
	    user.setLocation(userDTO.getLocation());
	    userRepo.saveAndFlush(user);
		
	}

	public String userLogin(LoginDTO loginDTO) {
		
		Optional<Users>  user= userRepo.findByUser(loginDTO.getUserName());
		if(user.isPresent()) {
			if(user.get().getUserPassword().equals(loginDTO.getUserPassword())) {
				return "Successfully Logged Inn";
			}
			else {
				return "Password Was Incorrect Please Try Again";
			}
		}
		else {
			return "We Can't Find Your Account please SignUp";
		}
		
	}

	public String updateUserRole(String userName,String role) {
		
		Optional<Users>  user= userRepo.findByUser(userName);
		if(user.isPresent()) {
			Users users=user.get();
			users.setUserRole(role);
			userRepo.saveAndFlush(users);
			
			return "details Updated SuccessFully";
			
		}
		else {
			return "We Can't Find Your Account please SignUp";
		}
		
	}

}
