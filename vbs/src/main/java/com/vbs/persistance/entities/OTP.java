/**
 * 
 */
package com.vbs.persistance.entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author DL
 *
 */

@Entity
@Table(name="OTP")
public class OTP {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OTP_ID")
	private long otpId;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;*/
	
	@Column(name="USER_ID")
	private long user_id;
	
	@Column(name="CODE", nullable=false)
	private long code;
	
	@Column(name="VERIFICATION_ATTEMPTS", nullable=false)
	private int verificationAttempts;
	
	@Column(name="GENERATION_ATTEMPTS", nullable=false)
	private int generationAttempts;
	
	@Column(name="PURPOSE", nullable=false)
	private String purpose;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXPIRY_DATETIME", nullable=false)
	private Date expiryDateTime;
	
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
	 * @return the otpId
	 */
	public long getOtpId() {
		return otpId;
	}

	/**
	 * @param otpId the otpId to set
	 */
	public void setOtpId(long otpId) {
		this.otpId = otpId;
	}
	
	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	/**
	 * @return the code
	 */
	public long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(long code) {
		this.code = code;
	}

	/**
	 * @return the verificationAttempts
	 */
	public int getVerificationAttempts() {
		return verificationAttempts;
	}

	/**
	 * @param verificationAttempts the verificationAttempts to set
	 */
	public void setVerificationAttempts(int verificationAttempts) {
		this.verificationAttempts = verificationAttempts;
	}

	/**
	 * @return the generationAttempts
	 */
	public int getGenerationAttempts() {
		return generationAttempts;
	}

	/**
	 * @param generationAttempts the generationAttempts to set
	 */
	public void setGenerationAttempts(int generationAttempts) {
		this.generationAttempts = generationAttempts;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return the expiryDateTime
	 */
	public Date getExpiryDateTime() {
		return expiryDateTime;
	}

	/**
	 * @param expiryDateTime the expiryDateTime to set
	 */
	public void setExpiryDateTime(Date expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
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
	@Override
	public String toString() {
		return "OTP [otpId=" + otpId + ", user=" + user_id + ", code=" + code
				+ ", verificationAttempts=" + verificationAttempts
				+ ", generationAttempts=" + generationAttempts + ", purpose="
				+ purpose + ", expiryDateTime=" + expiryDateTime
				+ ", createdBy=" + createdBy + ", createDate=" + createDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ "]";
	}
	
	
}
