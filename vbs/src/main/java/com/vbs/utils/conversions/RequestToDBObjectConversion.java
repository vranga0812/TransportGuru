package com.vbs.utils.conversions;

import com.vbs.persistance.entities.User;
import com.vbs.ui.pojos.UserForm;

/**
 * @author DL
 * Jun 21, 2015
 * 
 * This class will contain static methods that will be used to convert the objects coming 
 * 
 */
public class RequestToDBObjectConversion {

	public static User convertUserFormToDBUserObject(UserForm userForm){
		
		System.out.println("RequestToDBObjectConversion.convertUserFormToDBUserObject()");
		User user = new User();
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setCompnayName(userForm.getCompnayName());
		user.setAddress(userForm.getAddress());
		user.setNativeLanguage(userForm.getNativeLanguage());
		user.setEmail(userForm.getEmail());
		user.setPrimaryMobile(userForm.getPrimaryMobile());
		user.setSecondayMobile(userForm.getSecondayMobile());
		user.setUserName(userForm.getUserName());
		user.setPassword(userForm.getPassword());
		user.setUserType(userForm.getUserType());
		user.setRemarks(userForm.getRemarks());
		return user;
		
	}
	
}
