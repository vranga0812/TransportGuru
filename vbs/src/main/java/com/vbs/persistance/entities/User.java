/**
 * 
 */
package com.vbs.persistance.entities;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author DL
 *
 */

@Entity
@Table(name="USERS")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;
	
	@Column(name="COMPANY_NAME", nullable=true)
	private String compnayName;
	
	@OneToMany(fetch = FetchType.EAGER/*, mappedBy = "user"*/)
	@JoinColumn(name="USER_ID")
	private Set<Address> address;
	
	@OneToMany(fetch = FetchType.EAGER/*, mappedBy = "user"*/)
	@JoinColumn(name="USER_ID")
	private Set<OTP> otp;
	
	@Column(name="NATIVE_LANGUAGE")
	private String nativeLanguage;
	
	@Column(name="EMAIL", unique=true, nullable=false)
	private String email;
	
	@Column(name="PRIMARY_MOBILE", nullable=false)
	private String primaryMobile;
	
	@Column(name="SECONDARY_MOBILE")
	private String secondayMobile;
	
	@Column(name="USER_TYPE", nullable=false)
	private String userType;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	@Column(name="MOBILE_VERIFIED_FLAG", nullable=false)
	private char mobileVerifiedFlag;
	
	@Column(name="ID_VERIFIED_FLAG", nullable=false)
	private char idVerifiedFlag;
	
	@Column(name="CREATED_BY", nullable=false)
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createDate;
	
	@Column(name="UPDATED_BY", nullable=false)
	private String updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
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
	 * @return the otp
	 */
	public Set<OTP> getOtp() {
		return otp;
	}
	/**
	 * @param otp the address to set
	 */
	public void setOtp(Set<OTP> otp) {
		this.otp = otp;
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
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the mobileVerifiedFlag
	 */
	public char getMobileVerifiedFlag() {
		return mobileVerifiedFlag;
	}
	/**
	 * @param mobileVerifiedFlag the mobileVerifiedFlag to set
	 */
	public void setMobileVerifiedFlag(char mobileVerifiedFlag) {
		this.mobileVerifiedFlag = mobileVerifiedFlag;
	}
	/**
	 * @return the idVerifiedFlag
	 */
	public char getIdVerifiedFlag() {
		return idVerifiedFlag;
	}
	/**
	 * @param idVerifiedFlag the idVerifiedFlag to set
	 */
	public void setIdVerifiedFlag(char idVerifiedFlag) {
		this.idVerifiedFlag = idVerifiedFlag;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
/*	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", compnayName=" + compnayName
				+ ", address=" + address.size() + ", otp=" + otp.size() + ", nativeLanguage="
				+ nativeLanguage + ", email=" + email + ", primaryMobile="
				+ primaryMobile + ", secondayMobile=" + secondayMobile
				+ ", userType=" + userType + ", remarks=" + remarks
				+ ", status=" + status + ", mobileVerifiedFlag="
				+ mobileVerifiedFlag + ", idVerifiedFlag=" + idVerifiedFlag
				+ ", createdBy=" + createdBy + ", createDate=" + createDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ "]";
	}*/
}
