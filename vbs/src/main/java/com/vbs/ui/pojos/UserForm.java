/**
 * 
 */
package com.vbs.ui.pojos;


import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.vbs.custom.annotations.PhoneNumber;
import com.vbs.persistance.entities.Address;

/**
 * @author DL
 * Jun 20 2015
 * this class uses some of JPA and hibernate annotations
 */

public class UserForm {

	@NotNull
	@Size(min=2, max=50, message="{firstname.cannot.be.null}")
	private String firstName;
	
	@NotNull
	@Size(min=2, max=50, message="{lastname.cannot.be.null}")
	private String lastName;
	
	private String compnayName;
	
	@NotNull @NotEmpty
	private Set<Address> address;
	
	@NotNull
	private String nativeLanguage;
	
	@NotNull @NotEmpty @Email
	private String email;
	
	@PhoneNumber(optional=false)
	private String primaryMobile;
	
	private String secondayMobile;
	
	@NotNull @NotEmpty
	private String userName;
	
	@NotNull @NotEmpty
	private String password;
	
	@NotNull @NotEmpty
	private String userType;
	private String remarks;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the compnayName
	 */
	public String getCompnayName() {
		return compnayName;
	}
	/**
	 * @param compnayName the compnayName to set
	 */
	public void setCompnayName(String compnayName) {
		this.compnayName = compnayName;
	}
	/**
	 * @return the address
	 */
	public Set<Address> getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Set<Address> address) {
		this.address = address;
	}
	/**
	 * @return the nativeLanguage
	 */
	public String getNativeLanguage() {
		return nativeLanguage;
	}
	/**
	 * @param nativeLanguage the nativeLanguage to set
	 */
	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the primaryMobile
	 */
	public String getPrimaryMobile() {
		return primaryMobile;
	}
	/**
	 * @param primaryMobile the primaryMobile to set
	 */
	public void setPrimaryMobile(String primaryMobile) {
		this.primaryMobile = primaryMobile;
	}
	/**
	 * @return the secondayMobile
	 */
	public String getSecondayMobile() {
		return secondayMobile;
	}
	/**
	 * @param secondayMobile the secondayMobile to set
	 */
	public void setSecondayMobile(String secondayMobile) {
		this.secondayMobile = secondayMobile;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserForm [firstName=" + firstName + ", lastName=" + lastName
				+ ", compnayName=" + compnayName + ", address=" + address
				+ ", nativeLanguage=" + nativeLanguage + ", email=" + email
				+ ", primaryMobile=" + primaryMobile + ", secondayMobile="
				+ secondayMobile + ", userName=" + userName + ", password="
				+ password + ", userType=" + userType + ", remarks=" + remarks
				+ "]";
	}
	
	
}
