package com.mitrais.btpn.user;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 
 * @author muslim
 *
 */

@Entity(name="user")
public class User implements Serializable {
	
	/**
	 * Default serial version ID for user class
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER_TYPE_EMPLOYEE = "E";
	public static final String USER_TYPE_AFFILIATE = "A";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_type")
	private String userType;
	
	@Column(name="join_date")
	private Date joinDate;
	
	public User() {};
	
	public User(Integer userId, String userName, String userType, Date joinDate) {
		this.userId = userId;
		this.userName = userName;
		this.userType = userType;
		this.joinDate = joinDate;
	}
	
	public User(String userId, String userName, String userType, String joinDate) {
		try {
			this.userId = Integer.parseInt(userId);
			this.userName = userName;
			this.userType = userType;
			this.joinDate = sdf.parse(joinDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "User : {userId:" + userId + ", userName:" + userName + ", userType:" + userType + ", joinDate:" + joinDate
				+ "}";
	}
	
}
