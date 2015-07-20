package com.debtrepaymentapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.debtrepaymentapp.dao.DebtDAO;
import com.debtrepaymentapp.dao.DebtDAOImpl;
import com.debtrepaymentapp.dao.UserDAO;
import com.debtrepaymentapp.model.Debt;
import com.debtrepaymentapp.model.User;

@Controller
public class DebtViewController {

	@Autowired
    private DebtDAO debtDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User myUser;
	
	/**
	 * The landing page for this application.
	 * @param user
	 * @return LoginHome
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView loginPage(@ModelAttribute User user) { //the form in LoginHome is mapped to this user
		return new ModelAndView("LoginHome");
	}
	
	/**
	 * This method will set the values for the User bean and should only be called once.
	 * 
	 * @param user
	 * @param userName
	 * @param userPassword
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login")
	public ModelAndView getUserDebts(@ModelAttribute User user, @RequestParam String userName, @RequestParam String userPassword) throws IOException {
		
		Integer userID = userDAO.getUserId(userName, userPassword);
		
		myUser.setUserId(userID);
		myUser.setUserName(userName);
		myUser.setUserPassword(userPassword);
		//TODO add in email
		
	    return getDebtHome();
	}
	
	@RequestMapping(value = "/DebtForm", method = RequestMethod.POST)
	public ModelAndView newDebt(@ModelAttribute Debt debt) {
	    return new ModelAndView("DebtForm");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerUser(@ModelAttribute User user) {
		return new ModelAndView("Register");	
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult br) {
		
		ModelAndView result;
		boolean success = userDAO.createUser(user.getUserName(),user.getUserEmail(),user.getUserPassword(),user.getPasswordVerify());

	    if (success && !br.hasErrors()) {	    	
	    	result = new ModelAndView("redirect:/login");
	    	result.addObject("userName", user.getUserName());
	    	result.addObject("userPassword", user.getUserPassword());
	    } else {
	    	result = new ModelAndView("Register");
	    }
	    return result;
	}

	@RequestMapping(value = "/saveDebt", method = RequestMethod.POST)
	public ModelAndView saveDebt(@ModelAttribute Debt debt, @ModelAttribute User user) {
		ModelAndView result;
	    debtDAO.saveOrUpdate(debt, myUser.getUserId());
	    
	    try {
	    	result =  getDebtHome();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	
	    	//TODO This will probably never get executed...throw IOException instead?
	    	result = new ModelAndView("redirect:/login?userName="+myUser.getUserName()+"?userPassword="+myUser.getUserPassword());
	    }
	    return result;
	}
	
	@RequestMapping(value = "/deleteDebt", method = RequestMethod.GET)
	public ModelAndView deleteContact(@ModelAttribute Debt debt, @ModelAttribute User user, @RequestParam("debtName") String debtName) throws IOException {
		//System.out.println(debtName);
		debtDAO.delete(myUser.getUserId(), debt.getDebtName());
	    return getDebtHome();
	}
	
	@RequestMapping(value = "/editDebt")
	public ModelAndView editDebt(@ModelAttribute Debt debt) throws IOException {
		
		debtDAO.saveOrUpdate(debt, myUser.getUserId());
		
	    return new ModelAndView("DebtEditForm", "debt", debt);
	}
	
	
	/**
	 * Get the ModelAndView which displays the table of User Debts.
	 * By the time this method is called the myUser field has been set.
	 * 
	 * @return DebtHome
	 */
	private ModelAndView getDebtHome() throws IOException {
		ModelAndView result = new ModelAndView("DebtHome");
		List<Debt> listDebts = debtDAO.list(myUser.getUserId());
		result.addObject("userName", myUser.getUserName());
		result.addObject("listDebts",listDebts);
		return result;
	}
	
}
