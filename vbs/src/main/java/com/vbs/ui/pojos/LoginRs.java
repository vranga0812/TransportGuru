package com.vbs.ui.pojos;

import com.vbs.persistance.entities.User;

/**
 * @author DL
 * Jul 27, 2015
 */
public class LoginRs extends BaseRs {

	private static final long serialVersionUID = 8995080045120611308L;
	
	private User user;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
