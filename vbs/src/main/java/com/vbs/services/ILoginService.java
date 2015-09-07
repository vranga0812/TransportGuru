package com.vbs.services;

import com.vbs.custom.exceptions.VbsException;
import com.vbs.persistance.entities.User;

/**
 * @author DL
 * Jul 13, 2015
 */

/*
 * provide services for login, reset password
 * */
public interface ILoginService {

	public User verifyLogin(String username, String password) throws VbsException;
	
	public boolean resetPassword();
	
	
}
