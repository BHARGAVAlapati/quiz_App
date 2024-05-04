package com.h3m.User.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
	private String userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String userPassword;
	private String userEmail;
	private Long phnoneNo;
	private String location;
	private String userRole;
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Long getPhnoneNo() {
		return phnoneNo;
	}
	public void setPhnoneNo(Long phnoneNo) {
		this.phnoneNo = phnoneNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}
