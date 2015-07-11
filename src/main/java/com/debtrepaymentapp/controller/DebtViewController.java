package com.debtrepaymentapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	private Integer theID;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView loginPage(@ModelAttribute User user) { //the form in LoginHome is mapped to this user
		//user.setUserId(userId);
		return new ModelAndView("LoginHome");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView getUserDebts(@RequestParam String userName, @RequestParam String userPassword) throws IOException {
		
		theID = userDAO.getUserId(userName, userPassword);
		List<Debt> listDebts = debtDAO.list(theID.intValue());
	    return new ModelAndView("DebtHome","listDebts",listDebts);
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
	
	@RequestMapping(value = "/DebtForm", method = RequestMethod.GET)
	public ModelAndView newDebt(ModelAndView model, @ModelAttribute Debt debt) {
		//Debt newDebt = new Debt();
	    model.addObject("debt", debt);
	    model.setViewName("DebtForm");
	    return model;
	}
	
	@RequestMapping(value = "/deleteDebt", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
	    int debtID = Integer.parseInt(request.getParameter("id"));
	    debtDAO.delete(debtID);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/saveDebt", method = RequestMethod.POST)
	public ModelAndView saveDebt(@ModelAttribute Debt debt) {
	    debtDAO.saveOrUpdate(debt,theID);
	    return new ModelAndView("redirect:/");
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
