package com.vbs.services;

import com.vbs.custom.exceptions.AlreadyTakenException;
import com.vbs.custom.exceptions.VbsException;
import com.vbs.persistance.entities.User;
import com.vbs.persistance.entities.UserCredentials;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * This interface will have set of methods that will be used to register and activate a user
 * 
 */
public interface IRegistrationService {

	public boolean createNewUser(final UserCredentials cred) throws AlreadyTakenException;
	
	public boolean activateUser(String username, long otp) throws VbsException;
	
	public boolean generateOtp(User user, String purpose) throws VbsException;
	
	public boolean generateOtp(String userName, String purpose) throws VbsException;
	
	public boolean verifyOtp(User user, String OTP, String purpose) throws VbsException;
	
	public boolean verifyOtp(String username, long OTP, String purpose) throws VbsException;

	public boolean verifyOtp(long userId, String OTP, String purpose) throws VbsException;
	
}
