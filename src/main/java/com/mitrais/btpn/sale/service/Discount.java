package com.mitrais.btpn.sale.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.btpn.user.User;
import com.mitrais.btpn.user.dao.UserDAO;
import com.mitrais.btpn.user.service.UserService;

/**
 * 
 * @author muslim
 * @category Discount Utiliy Class
 *
 */

@Service
public class Discount {
	
	@Autowired
	private UserService userService;

	private double employeeDiscount;
	private double affiliateDiscount;
	private double moreThan2YearsDiscount;

	public Discount() {};
	
	public Discount(double employeeDiscount, double affiliateDiscount, double moreThan2YearsDiscount, UserService userService) {
		this.employeeDiscount = employeeDiscount;
		this.affiliateDiscount = affiliateDiscount;
		this.moreThan2YearsDiscount = moreThan2YearsDiscount;
		this.userService = userService;
	}
	
	/**
	 * Calculate Discount based on User Type
	 * @param user
	 * @return discount value in percentage
	 */
	public double discountByUserTypeOnPercentage(User user) {
		double discountVal = 0.0;
		
		switch (user.getUserType()) {
		case User.USER_TYPE_EMPLOYEE:
			discountVal = this.employeeDiscount;
			break;

		case User.USER_TYPE_AFFILIATE:
			discountVal = this.affiliateDiscount;
			break;
			
		default:
			break;
		}
		
		return discountVal;
	}

	
	/**
	 * Calculate discount for more thant 2 years user
	 * @param user
	 * @return discount on percentage value
	 */
	public double discountByYearsNumOnPercentage(User user) {
		double discountVal = 0.0;
		
		if (user != null && isMoreThan2Years(user.getJoinDate())) {
			discountVal = this.moreThan2YearsDiscount;
		} 
		
		return discountVal;
	}
	
	private boolean isMoreThan2Years(Date compareDate) {
		LocalDate currentDate = LocalDate.now();
		Date twoYearsDate = Date.from(currentDate.minusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
		return twoYearsDate.compareTo(compareDate) == 1;
	}

	/**
	 * Calculate discount for every hundred bills
	 * @param paidBills
	 * @return discount value in dollar bills
	 */
	public double discountByHundredBillsOnDollars(double paidBills) {
		double discountVal = 0.0;
		
		if (paidBills >= 100) {
			discountVal = ((paidBills - (paidBills%100))/100) * 5;
		}
		
		return discountVal;
	}
	
	/**
	 * Calculate net payable amount for any particular user 
	 * @param user
	 * @param paidBills
	 * @param isGroceries
	 * @return
	 */
	public double netPayableAmount(Integer userId, double chargedBill, boolean isGroceries) {
		User user = userService.findByUserId(userId);
		
		if (user == null) {
			List<User> userList = userService.readUserFromFile();
			for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
				User us = iterator.next();
				if (userId == us.getUserId()) {
					user = us;
				}
			}
		}
		
		double percentDiscount = 0.0;
		
		if ( user != null && !isGroceries) {
			percentDiscount = discountByUserTypeOnPercentage(user);
			percentDiscount += discountByYearsNumOnPercentage(user);
		} 
		
		double netAmount = percentDiscount > 0.0 ? chargedBill - ((percentDiscount*chargedBill)/100) : chargedBill;
		return netAmount - discountByHundredBillsOnDollars(chargedBill);
	}
	
}
