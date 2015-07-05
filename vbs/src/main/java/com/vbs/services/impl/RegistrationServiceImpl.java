package com.vbs.services.impl;

import static com.vbs.utils.Constants.COLON;
import static com.vbs.utils.Constants.INITIAL_OTP_GENERATION_ATTEMPTS;
import static com.vbs.utils.Constants.INITIAL_OTP_VERIFICATION_ATTEMPTS;
import static com.vbs.utils.Constants.MAX_OTP_GENERATION_ATTEMPTS;
import static com.vbs.utils.Constants.MAX_OTP_VERIFICATION_ATTEMPTS;
import static com.vbs.utils.Constants.N;
import static com.vbs.utils.Constants.OTP_EXPIRATION_IN_SEC;
import static com.vbs.utils.Constants.OTP_LENGHT;
import static com.vbs.utils.Constants.PROFILE_ACTIVATION;
import static com.vbs.utils.Constants.USER_PROFILE_STATUS_ACTIVE;
import static com.vbs.utils.Constants.USER_PROFILE_STATUS_LOCKED;
import static com.vbs.utils.Constants.USER_PROFILE_STATUS_PENDING_ACTIVATION;
import static com.vbs.utils.Constants.VBS_SYSTEM;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.vbs.custom.exceptions.AlreadyTakenException;
import com.vbs.custom.exceptions.UserNameAlreadyTakenException;
import com.vbs.custom.exceptions.VbsException;
import com.vbs.persistance.dao.DatabaseDao;
import com.vbs.persistance.entities.Address;
import com.vbs.persistance.entities.OTP;
import com.vbs.persistance.entities.User;
import com.vbs.security.opt.OTPGenerator;
import com.vbs.services.RegistrationService;

/**
 * @author DL
 * Jun 21, 2015
 */

@Service
@Primary
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	DatabaseDao dbOps;
	
	@Autowired
	OTPGenerator otpGenerator;
	
	
	
	/**
	 * @return the dbOps
	 */
	public DatabaseDao getDbOps() {
		return dbOps;
	}

	/**
	 * @param dbOps the dbOps to set
	 */
	public void setDbOps(DatabaseDao dbOps) {
		this.dbOps = dbOps;
	}

	/**
	 * @return the otpGenerator
	 */
	public OTPGenerator getOtpGenerator() {
		return otpGenerator;
	}

	/**
	 * @param otpGenerator the otpGenerator to set
	 */
	public void setOtpGenerator(OTPGenerator otpGenerator) {
		this.otpGenerator = otpGenerator;
	}
	

	/* (non-Javadoc)
	 * @see com.vbs.services.RegistrationService#createNewUser(com.vbs.persistance.entities.User)
	 */
	@Override
	public boolean createNewUser(User user) throws AlreadyTakenException {
		
		System.out.println("RegistrationServiceImpl.createNewUser()");
		
		System.out.println("Checking if the username is available...");
		
		if(!dbOps.isUserNameAvailable(user.getUserName()))
			throw new UserNameAlreadyTakenException("Username is not available");
		
		boolean result = false;
		
		user.setMobileVerifiedFlag(N);
		user.setIdVerifiedFlag(N);
		user.setCreatedBy(VBS_SYSTEM+COLON+user.getUserName());
		user.setUpdatedBy(VBS_SYSTEM+COLON+user.getUserName());
		user.setStatus(USER_PROFILE_STATUS_PENDING_ACTIVATION);
		
		Set<Address> addresses = user.getAddress();
		for(Address address : addresses){
			address.setCreatedBy(VBS_SYSTEM+COLON+user.getUserName());
			address.setUpdatedBy(VBS_SYSTEM+COLON+user.getUserName());
		}
		
		user.setAddress(null);
		
		OTP otp = getOTPInstance(user, PROFILE_ACTIVATION);
		
		dbOps.createUser(user, addresses, otp);
		//TODO: Write code to send OTP to customer via SMS
		return result;
		
	}

	@Override
	public boolean generateOtp(User user, String purpose) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vbs.services.RegistrationService#generateOtp(java.lang.String)
	 */
	@Override
	public boolean generateOtp(String username, String purpose) throws VbsException {
		//Generating or regenerating OTP based on username
		boolean isOtpGenerated = false;
		if(null==purpose || "".equals(purpose.trim()))
			throw new VbsException("purpose field is mandatory to verify OTP");
		
		//getting the user profile object based on username
		User user = dbOps.getCustomerProfile(username);
		
		//if the user profile is null based on username, exception is thrown
		if(user == null){
			throw new VbsException("USER_NOT_FOUND","INVALID USER NAME");
		}
		
		
		//Getting OTP that is generated for OTP. During regeneration, NO NEW record should be created. 
		OTP profileActivationOTP = null;
		for(OTP otp : user.getOtp()){
			if(purpose.equals(otp.getPurpose())){
				profileActivationOTP = otp;
				break;
			}
		}
		
		//check if there is already record in DB for the given purpose. if exists update existing if not create new
		if(profileActivationOTP == null){
			profileActivationOTP = getOTPInstance(user, purpose);
			isOtpGenerated = true;
		}else{
			if(profileActivationOTP.getGenerationAttempts()>=MAX_OTP_GENERATION_ATTEMPTS){
				throw new VbsException("MAX_GENERATION_ATTEMPTS", "CALL CUSTOMER SERVICE TO REGENERATE OTP");
			}else{
				// if the record exists for the given purpose, reset the verification attempts, set new expiration date 
				// and increment generation attempts 
				profileActivationOTP.setGenerationAttempts(profileActivationOTP.getGenerationAttempts()+1);
				profileActivationOTP.setVerificationAttempts(0);
				profileActivationOTP.setExpiryDateTime(Date.from(Instant.now().plusSeconds(OTP_EXPIRATION_IN_SEC)));
			}
		}
		dbOps.saveOrUpdate(profileActivationOTP);		
		//TODO: Write code to send the OTP to customer via sms
		return isOtpGenerated;
	}

	/* (non-Javadoc)
	 * @see com.vbs.services.RegistrationService#verifyOtp(com.vbs.persistance.entities.User, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean verifyOtp(User user, String OTP, String purpose) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vbs.services.RegistrationService#verifyOtp(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean verifyOtp(String username, long OTP, String purpose) throws VbsException {
		boolean isValidOtp = false;
		if(null==purpose || "".equals(purpose.trim()))
			throw new VbsException("purpose field is mandatory to verify OTP");
		
		//getting the user profile object based on username
		User user = dbOps.getCustomerProfile(username);
		
		//if the user profile is null based on username, exception is thrown
		if(user == null){
			throw new VbsException("USER_NOT_FOUND","INVALID USER NAME");
		}
		
		//Getting OTP that is generated for OTP. During regeneration, NO NEW record should be created. 
		OTP profileActivationOTP = null;
		for(OTP otp : user.getOtp()){
			if(purpose.equals(otp.getPurpose())){
				profileActivationOTP = otp;
				break;
			}
		}
		
		//TODO: Need to update based on reason user requesting the OTP for
		if(purpose.equals(PROFILE_ACTIVATION) && !USER_PROFILE_STATUS_PENDING_ACTIVATION.equals(user.getStatus())){
			throw new VbsException("STATUS","PROFILE_IS_NOT_IN_VALID_STATUS");
		}
		
		//If the OTP is not available error is thrown
		if(profileActivationOTP == null){
			throw new VbsException("NO_OTP","NO "+PROFILE_ACTIVATION+" OTP");
		}
		try{
			if(profileActivationOTP.getVerificationAttempts()<MAX_OTP_VERIFICATION_ATTEMPTS){
				//increasing OTP attempts count, irrespective of valid otp, expired otp
				profileActivationOTP.setVerificationAttempts(profileActivationOTP.getVerificationAttempts()+1);
				if(profileActivationOTP.getCode()==OTP){
					if(profileActivationOTP.getExpiryDateTime().before(new Date())){
						//setting user status to active
						user.setStatus(USER_PROFILE_STATUS_ACTIVE);
						isValidOtp = true;
					}else{
						//handle OTP expiration logic  
						throw new VbsException("OTP_EXPIRED", "OTP HAS EXPIRED, Please request for new OTP");
					}
				}else{ //invalid OTP code passed
					if(profileActivationOTP.getVerificationAttempts()==MAX_OTP_VERIFICATION_ATTEMPTS){
						user.setStatus(USER_PROFILE_STATUS_LOCKED);
						throw new VbsException("INVALID_OTP", "OTP IS NOT VALID");
					}
				}
			} else {
				isValidOtp = false;
			}
		}catch(VbsException e){
			throw e;
		}finally{
			dbOps.saveOrUpdate(user);
			dbOps.saveOrUpdate(profileActivationOTP);
		}
		return isValidOtp;
	}
	
	@Override
	public boolean verifyOtp(long userId, String OTP, String purpose) throws VbsException {
		if(null==purpose || "".equals(purpose.trim()))
			throw new VbsException("purpose field is mandatory to verify OTP");
		
		
		return false;
		
	}

	
	
	/* (non-Javadoc)
	 * @see com.vbs.services.RegistrationService#activateUser(long, long)
	 */
	@Override
	public boolean activateUser(String username, long otpCode) throws VbsException {
		return verifyOtp(username, otpCode, PROFILE_ACTIVATION);	
	}

	private OTP getOTPInstance(User user, String purpose){
		long otpCode = otpGenerator.generateOTP(OTP_LENGHT);
		OTP otp = new OTP();
		//otp.setUser(user);
		otp.setCode(otpCode);
		otp.setVerificationAttempts(INITIAL_OTP_VERIFICATION_ATTEMPTS);
		otp.setGenerationAttempts(INITIAL_OTP_GENERATION_ATTEMPTS);
		otp.setExpiryDateTime(Date.from(Instant.now().plusSeconds(OTP_EXPIRATION_IN_SEC)));
		otp.setPurpose(purpose);
		otp.setCreatedBy(VBS_SYSTEM+COLON+user.getUserName());
		otp.setUpdatedBy(VBS_SYSTEM+COLON+user.getUserName());
		otp.setUser_id(user.getUserId());
		
		return otp;
	}
}
