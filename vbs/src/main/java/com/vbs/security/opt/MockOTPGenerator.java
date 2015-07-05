package com.vbs.security.opt;

import org.springframework.stereotype.Component;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * This class is only for testing purpose only. This will always return mocked otp
 * 
 */

@Component
public class MockOTPGenerator implements OTPGenerator {

	/* (non-Javadoc)
	 * @see com.vbs.security.opt.OTPGenerator#generateOTP(int)
	 */
	@Override
	public long generateOTP(int lenght) {
		return 12345678;
	}

}
