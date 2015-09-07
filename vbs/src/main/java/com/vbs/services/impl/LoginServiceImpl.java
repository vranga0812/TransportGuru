package com.vbs.services.impl;

import static com.vbs.utils.Constants.ERROR_CODE_1001;
import static com.vbs.utils.Constants.ERROR_CODE_1002;
import static com.vbs.utils.Constants.ERROR_CODE_1003;
import static com.vbs.utils.Constants.ERROR_CODE_1004;
import static com.vbs.utils.Constants.ERROR_CODE_DESC_1001;
import static com.vbs.utils.Constants.ERROR_CODE_DESC_1002;
import static com.vbs.utils.Constants.ERROR_CODE_DESC_1003;
import static com.vbs.utils.Constants.ERROR_CODE_DESC_1004;
import static com.vbs.utils.Constants.MAX_INVALID_LOGIN_ATTEMPTS;
import static com.vbs.utils.Constants.USER_PROFILE_STATUS_ACTIVE;
import static com.vbs.utils.Constants.USER_PROFILE_STATUS_LOCKED;
import static com.vbs.utils.Constants.USER_PROFILE_STATUS_PENDING_ACTIVATION;
import static com.vbs.utils.Constants.Y;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.vbs.custom.exceptions.VbsException;
import com.vbs.persistance.dao.DatabaseDao;
import com.vbs.persistance.entities.User;
import com.vbs.persistance.entities.UserCredentials;
import com.vbs.services.ILoginService;

/**
 * @author DL
 * Jul 13, 2015
 */
public class LoginServiceImpl implements ILoginService {

	

	@Autowired
	DatabaseDao dbOps;
	
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
	/* (non-Javadoc)
	 * @see com.vbs.services.ILoginService#verifyLogin(java.lang.String)
	 */
	@Override
	public User verifyLogin(String username, String password) throws VbsException{
		User user=null;
		
		//1. Check the status of the wallet
		//2. if wallet is locked/blocked etc.., return error
		//3. if the password is temp password, return error
		//4. if the username is valid, return user
		//5. if the username is invalid, increment invalid login count return error code
		
		UserCredentials uc = dbOps.getCustomerProfile(username);
		if(uc==null)
			throw new VbsException(ERROR_CODE_1001, ERROR_CODE_DESC_1001);
			
		if(!password.equals(uc.getPassword())){
			uc.setLoginAttempts(uc.getLoginAttempts()+1);
			if(uc.getLoginAttempts()==MAX_INVALID_LOGIN_ATTEMPTS-1){
				uc.getUser().setStatus(USER_PROFILE_STATUS_LOCKED);
				dbOps.saveOrUpdate(uc.getUser());
				dbOps.saveOrUpdate(uc);
				throw new VbsException(ERROR_CODE_1002, ERROR_CODE_DESC_1002);
			}
			dbOps.saveOrUpdate(uc);
			throw new VbsException(ERROR_CODE_1001, ERROR_CODE_DESC_1001);
		}
		
		if(!USER_PROFILE_STATUS_PENDING_ACTIVATION.equals(uc.getUser().getStatus()) 
				|| !USER_PROFILE_STATUS_ACTIVE.equals(uc.getUser().getStatus()))
			throw new VbsException(ERROR_CODE_1003, ERROR_CODE_DESC_1003);
		
		uc.setLastLoginTime(new Date());
		uc.setLoginAttempts(0);
		dbOps.saveOrUpdate(uc);
		
		if(Y==uc.getIsChangePassword()){
			throw new VbsException(ERROR_CODE_1004, ERROR_CODE_DESC_1004);
		}
		
		return user;
	}

	/* (non-Javadoc)
	 * @see com.vbs.services.ILoginService#resetPassword()
	 */
	@Override
	public boolean resetPassword() {
		// TODO Auto-generated method stub
		return false;
	}

}
