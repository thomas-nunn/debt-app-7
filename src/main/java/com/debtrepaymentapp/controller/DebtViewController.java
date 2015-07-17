package com.debtrepaymentapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView loginPage(@ModelAttribute User user) { //the form in LoginHome is mapped to this user
		return new ModelAndView("LoginHome");
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView getUserDebts(@ModelAttribute User user, @RequestParam String userName, @RequestParam String userPassword) throws IOException {
		
		Integer userID = userDAO.getUserId(userName, userPassword);
		
		myUser.setUserId(userID);
		myUser.setUserName(userName);
		myUser.setUserPassword(userPassword);
		//TODO add in email
		
	    return getDebtHome(userID);
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
	public ModelAndView saveUser(@ModelAttribute User user) {
	    debtDAO.createUser(user);
	    return new ModelAndView("redirect:/");
	}
	

	
	@RequestMapping(value = "/deleteDebt", method = RequestMethod.GET)
	public ModelAndView deleteContact(@ModelAttribute Debt debt, @ModelAttribute User user, @RequestParam("debtName") String debtName ) throws IOException {
		System.out.println(debtName);
		debtDAO.delete(myUser.getUserId(), debt.getDebtName());
	    return getDebtHome(myUser.getUserId());
	}
	
	@RequestMapping(value = "/saveDebt", method = RequestMethod.POST)
	public ModelAndView saveDebt(@ModelAttribute Debt debt, @ModelAttribute User user) {
		ModelAndView result;
	    debtDAO.saveOrUpdate(debt, myUser.getUserId());
	    
	    try {
	    	result =  getDebtHome(myUser.getUserId());
	    } catch(IOException e) {
	    	e.printStackTrace();
	    	result = new ModelAndView("redirect:/login?userName="+myUser.getUserName()+"?userPassword="+myUser.getUserPassword());
	    } catch(DuplicateKeyException dke) {
	    	//dke.printStackTrace();
	    	result = new ModelAndView("redirect:/login?userName="+myUser.getUserName()+"?userPassword="+myUser.getUserPassword());	    	
	    }
	    return result;
	}
	
	/**
	@RequestMapping(value = "/editDebt", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
	    int debtID = Integer.parseInt(request.getParameter("id"));
	    Debt debt = debtDAO.get(debtID);
	    ModelAndView model = new ModelAndView("ContactForm");
	    model.addObject("debt", debt);
	 
	    return model;
	}
	*/
	
	/**
	 * Get the ModelAndView which displays the table of User Debts.
	 * @return DebtHome
	 */
	private ModelAndView getDebtHome(int userId) throws IOException {
		ModelAndView result = new ModelAndView("DebtHome");
		List<Debt> listDebts = debtDAO.list(userId);
		result.addObject("userName", myUser.getUserName());
		result.addObject("listDebts",listDebts);
		return result;
	}
	
}
