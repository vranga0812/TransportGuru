package com.vbs.ui.pojos;

import java.io.Serializable;

/**
 * @author DL
 * Jul 27, 2015
 */
public class BaseRq implements Serializable{

	//Base request that every request class must extend
	
	private static final long serialVersionUID = -1342705290071875420L;
	
	
	private String clientId;
	private String clientPassword;
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the clientPassword
	 */
	public String getClientPassword() {
		return clientPassword;
	}
	/**
	 * @param clientPassword the clientPassword to set
	 */
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	
	
	
}
