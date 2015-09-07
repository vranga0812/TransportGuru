package com.vbs.utils.conversions;

import static com.vbs.utils.Constants.COLON;
import static com.vbs.utils.Constants.N;
import static com.vbs.utils.Constants.USER_PROFILE_STATUS_PENDING_ACTIVATION;
import static com.vbs.utils.Constants.VBS_SYSTEM;

import java.util.Set;

import com.vbs.persistance.entities.Address;
import com.vbs.persistance.entities.User;
import com.vbs.persistance.entities.UserCredentials;
import com.vbs.ui.pojos.CreateUserRq;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * This class will contain static methods that will be used to convert the objects coming 
 * 
 */
public class RequestToDBObjectConversion {

	public static UserCredentials convertUserFormToDBUserCredentialsObject(CreateUserRq createUserRq){
		
		System.out.println("RequestToDBObjectConversion.convertUserFormToDBUserObject()");
		UserCredentials cred = new UserCredentials();
		User user = new User();
		user.setFirstName(createUserRq.getFirstName());
		user.setLastName(createUserRq.getLastName());
		user.setCompnayName(createUserRq.getCompnayName());
		user.setAddress(createUserRq.getAddress());
		user.setNativeLanguage(createUserRq.getNativeLanguage());
		user.setEmail(createUserRq.getEmail());
		user.setPrimaryMobile(createUserRq.getPrimaryMobile());
		user.setSecondayMobile(createUserRq.getSecondayMobile());
		user.setUserType(createUserRq.getUserType());
		user.setRemarks(createUserRq.getRemarks());
		user.setMobileVerifiedFlag(N);
		user.setIdVerifiedFlag(N);
		user.setCreatedBy(VBS_SYSTEM+COLON+cred.getUsername());
		user.setUpdatedBy(VBS_SYSTEM+COLON+cred.getUsername());
		user.setStatus(USER_PROFILE_STATUS_PENDING_ACTIVATION);
		
		Set<Address> addresses = user.getAddress();
		for(Address address : addresses){
			address.setCreatedBy(VBS_SYSTEM+COLON+cred.getUsername());
			address.setUpdatedBy(VBS_SYSTEM+COLON+cred.getUsername());
		}
		System.out
				.println("RequestToDBObjectConversion.convertUserFormToDBUserCredentialsObject(): addresses="+user.getAddress());
		cred.setUsername(createUserRq.getUserName());
		cred.setPassword(createUserRq.getPassword());
		cred.setLoginAttempts(0);
		cred.setIsChangePassword('N');
		cred.setLastLoginTime(null);
		cred.setUser(user);
		cred.setCreatedBy(VBS_SYSTEM+COLON+cred.getUsername());
		cred.setUpdatedBy(VBS_SYSTEM+COLON+cred.getUsername());
	
		
		return cred;
		
	}
	
}
