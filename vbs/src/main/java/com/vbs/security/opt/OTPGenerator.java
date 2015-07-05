package com.vbs.security.opt;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * This interface will have methods for generating OTP
 * 
 */



public interface OTPGenerator {

	public long generateOTP(int lenght);
	
}
