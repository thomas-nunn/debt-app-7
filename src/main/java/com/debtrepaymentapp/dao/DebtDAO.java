package com.debtrepaymentapp.dao;

import java.util.List;

import com.debtrepaymentapp.model.Debt;
import com.debtrepaymentapp.model.User;


public interface DebtDAO {

	public void saveOrUpdate(Debt debt,Integer userID);
    
    public void delete(int userID, String debtName);
     
    public Debt get(int userID, String debtName);
     
    public List<Debt> list(int userID);
    
    public void createUser(User user);
}
