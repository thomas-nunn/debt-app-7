package com.debtrepaymentapp.dao;

import java.util.List;

import com.debtrepaymentapp.model.Debt;
import com.debtrepaymentapp.model.User;


public interface DebtDAO {

	public void saveOrUpdate(Debt debt,Integer userID);
    
    public void delete(int debtID);
     
    public Debt get(int debtID);
     
    public List<Debt> list(int userID);
    
    public void createUser(User user);
}
