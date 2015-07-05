package com.vbs.utils;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * Will contain all public static final strings that will be used in the project
 * 
 */

public class Constants {
	
	public static final String VBS_SYSTEM = "VBS_SYSTEM";
	public static final String REGISTRATION_VERIFICATION = "REGISTRATION_VERIFICATION";
	public static final String COLON = ":";
	public static final String EMPTY_STRING="";
	public static final String DEFAULT_PHONE_NUM_VALIDATION_ERROR="Please enter valid phone number";
	public static final String PROFILE_ACTIVATION= "PROFILE_ACTIVATION";
	public static final String USER_PROFILE_STATUS_PENDING_ACTIVATION = "PENDING_ACTIVATION";
	public static final String USER_PROFILE_STATUS_ACTIVE = "ACTIVE";
	public static final String USER_PROFILE_STATUS_LOCKED="LOCKED";

	
	public static final char PLUS = '+';
	public static final char Y = 'Y';
	public static final char N = 'N';
	
	public static final int OTP_LENGHT = 8;
	public static final int INITIAL_OTP_GENERATION_ATTEMPTS = 1;
	public static final int INITIAL_OTP_VERIFICATION_ATTEMPTS = 1;
	public static final int MAX_OTP_VERIFICATION_ATTEMPTS = 3;
	public static final int MAX_OTP_GENERATION_ATTEMPTS = 3;
	public static final int OTP_EXPIRATION_IN_SEC=300;
	public static final int PHONE_NUMBER_LENGTH_WITH_PLUS_AND_COUNTRY_CODE=13;

}
