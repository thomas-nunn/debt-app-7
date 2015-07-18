package com.debtrepaymentapp.dao;

public interface UserDAO {
	
	public boolean createUser(String userName, String userPassword, String passwordVerify, String userEmail);

	public int getUserId(String userName, String userPassword);
}
