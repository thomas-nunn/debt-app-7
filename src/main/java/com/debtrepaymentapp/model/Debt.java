package com.debtrepaymentapp.model;

import javax.validation.constraints.Size;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

public class Debt {

    private int userID;
    
    @NotNull
    @DecimalMax("999999999.99")
    private Double payment;
    
    @NotNull
    @DecimalMax("999999999.99")
    private Double balance;
    
    @NotNull
    @DecimalMax("99.99")
    private Double rate;
    
    @NotNull
    @Size(min=1,max=40)
    private String debtName;
    
    public Debt() {   	
    }
    
    public Debt(int theUserID,Double thePayment,Double theBalance,Double theRate,String theDebtName) {
    	userID = theUserID;
    	payment = thePayment;
    	balance = theBalance;
    	rate = theRate;
    	debtName = theDebtName;
    }

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double ir) {
		this.rate = ir;
	}

	public String getDebtName() {
		return debtName;
	}

	public void setDebtName(String debtName) {
		this.debtName = debtName;
	}
}
