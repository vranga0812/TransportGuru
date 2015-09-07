package com.vbs.ui.pojos;

import java.io.Serializable;

/**
 * @author DL
 * Jul 4, 2015
 */
public class BaseRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1708462342879238151L;
	
	private String responseCode;
	private String responseMessage;
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}
	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	
}
