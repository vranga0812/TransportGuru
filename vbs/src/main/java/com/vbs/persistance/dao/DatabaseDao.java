package com.vbs.persistance.dao;

import java.util.Set;

import com.vbs.custom.exceptions.VbsException;
import com.vbs.persistance.entities.Address;
import com.vbs.persistance.entities.OTP;
import com.vbs.persistance.entities.User;

/**
 * @author DL
 * July 19th 2015
 * This interface is to perform database operations
 */
public interface DatabaseDao {

	// saves all the three objects to the VBS database
	public abstract boolean createUser(User user, Set<Address> addresses, OTP otp);
	
	//Implementation should save the profile to user info Database
	public abstract void saveOrUpdate(Object obj);
	
	//Checks if he username if is available or not
	public abstract boolean isUserNameAvailable(String username);
	
	//retrieve the customer profile using username
	public abstract User getCustomerProfile(String username);
	
	//retrieve the customer profile using email. implementation should throw error if is email parameter is false
	public abstract User getCustomerProfile(String email, boolean isEmail) throws VbsException;
	
	//takes OTP and the reason for generating OTP and will save it to OTP DB for the given profile
	public abstract void saveOTP(User user, String otp);
	
	//takes OTP and the reason for generating OTP and will save it to OTP DB for the given profile
	public abstract void saveOTP(String username, String otp);
}
