package com.vbs.persistance.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author DL
 * Jul 23, 2015
 */

@Entity
@Table(name="USER_CREDENTIALS")
public class UserCredentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="foreignKey", strategy="foreign", parameters=@Parameter(value="user", name = "property"))
	@GeneratedValue(generator="foreignKey")
	@Column(name="USER_CREDENTIAL_ID")
	private long userCredentailId;
	
	@Column(name="USER_NAME")
	private String username;          		
	
	@Column(name="PASSWORD")
	private String password;           		
	
	@Column(name="LOGIN_ATTEMPTS")
	private int loginAttempts;			
	
	@Column(name="IS_CHANGE_PASSWORD")
	private char isChangePassword;		
	
	@Column(name="LAST_LOGIN_TIME")
	private Date lastLoginTime;	
	
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

	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
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

	/**
	 * @return the userCredentailId
	 */
	public long getUserCredentailId() {
		return userCredentailId;
	}

	/**
	 * @param userCredentailId the userCredentailId to set
	 */
	public void setUserCredentailId(long userCredentailId) {
		this.userCredentailId = userCredentailId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the loginAttempts
	 */
	public int getLoginAttempts() {
		return loginAttempts;
	}

	/**
	 * @param loginAttempts the loginAttempts to set
	 */
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	/**
	 * @return the isChangePassword
	 */
	public char getIsChangePassword() {
		return isChangePassword;
	}

	/**
	 * @param isChangePassword the isChangePassword to set
	 */
	public void setIsChangePassword(char isChangePassword) {
		this.isChangePassword = isChangePassword;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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
	      		
	
}
