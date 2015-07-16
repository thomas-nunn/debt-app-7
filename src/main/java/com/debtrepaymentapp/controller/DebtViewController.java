package com.debtrepaymentapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ModelAndView getUserDebts(ModelAndView model, @ModelAttribute User user, @RequestParam String userName, @RequestParam String userPassword) throws IOException {
		
		Integer userID = userDAO.getUserId(userName, userPassword);
		List<Debt> listDebts = debtDAO.list(userID.intValue());
		
		myUser.setUserId(userID);
		myUser.setUserName(userName);
		myUser.setUserPassword(userPassword);
		//TODO add in email
		
		model.addObject("listDebts", listDebts);
		model.setViewName("DebtHome");
	    return model;
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
	public ModelAndView deleteContact(HttpServletRequest request) {
	    int debtID = Integer.parseInt(request.getParameter("id"));
	    debtDAO.delete(debtID);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/saveDebt", method = RequestMethod.POST)
	public ModelAndView saveDebt(@ModelAttribute Debt debt,@ModelAttribute User user) {
		
	    debtDAO.saveOrUpdate(debt, myUser.getUserId());
	    List<Debt> listDebts = debtDAO.list(myUser.getUserId());
	    return new ModelAndView("DebtHome","listDebts",listDebts);
	}
	
	@RequestMapping(value = "/editDebt", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
	    int debtID = Integer.parseInt(request.getParameter("id"));
	    Debt debt = debtDAO.get(debtID);
	    ModelAndView model = new ModelAndView("ContactForm");
	    model.addObject("debt", debt);
	 
	    return model;
	}
	

}
