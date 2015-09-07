package com.vbs.spring.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vbs.custom.exceptions.VbsException;
import com.vbs.persistance.entities.User;
import com.vbs.services.ILoginService;
import com.vbs.ui.pojos.LoginRq;

/**
 * @author DL
 * Aug 16, 2015
 */

@Controller
public class LoginController {

	@Autowired
	ILoginService loginService;

	/**
	 * @return the loginService
	 */
	public ILoginService getLoginService() {
		return loginService;
	}

	/**
	 * @param loginService the loginService to set
	 */
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<User> login(LoginRq rq) throws VbsException{
		
		System.out.println("LoginController.login()");
		
		User u = loginService.verifyLogin(rq.getUsername(), rq.getPassword());
		
		System.out.println("LoginController.login()->user: "+u);
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
		
	}
	
}