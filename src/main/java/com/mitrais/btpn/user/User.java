package com.mitrais.btpn.user;

import java.util.Date;

/**
 * 
 * @author muslim
 *
 */

public class User {
	
	public static final String USER_TYPE_EMPLOYEE = "E";
	public static final String USER_TYPE_AFFILIATE = "A";
	
	
	private String userType;
	private Date joinDate;
	
	
	public User(String userType, Date joinDate) {
		this.userType = userType;
		this.joinDate = joinDate;
	}

	public String getUserType() {
		return userType;
	}

	public Date getJoinDate() {
		return joinDate;
	}

}
