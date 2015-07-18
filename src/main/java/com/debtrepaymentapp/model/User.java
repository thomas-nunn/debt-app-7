package com.debtrepaymentapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	
	private int userId;
	
	@NotNull
	@Size(min=2, max=30)
	private String userName;
	
	@NotNull
	private String userEmail;
	
	@NotNull
	@Size(min=6, max=15)
	private String userPassword;
	
	@NotNull
	@Size(min=6, max=15)
	private String passwordVerify;
	
	public User() {
	}
	
	public User(String name, String email, String password) {
		userName = name;
		userEmail = email;
		setUserPassword(password);
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the passwordVerify
	 */
	public String getPasswordVerify() {
		return passwordVerify;
	}

	/**
	 * @param passwordVerify the passwordVerify to set
	 */
	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}
}
