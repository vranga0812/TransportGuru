package com.vbs.security.opt;

import java.security.SecureRandom;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author DL
 * Jun 21, 2015
 */

@Component
@Primary
public class SecureRandomOTPGenerator implements OTPGenerator {

	/* (non-Javadoc)
	 * @see com.vbs.security.opt.OTPGenerator#generateOTP(int)
	 */
	@Override
	public long generateOTP(int lenght) {
		//TODO: Need to change the OTP with more sophisticated algorithm. 
		// also write methods to generate numeric and alpha numeric OTP  
		SecureRandom randomNumberGenerator = new SecureRandom();
		long rand = randomNumberGenerator.nextInt(99999999);
		if(rand<0)
			rand = -rand;
		System.out.println(String.valueOf(rand));;
		return rand;
		
	}

}
