package com.mitrais.btpn.sale;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mitrais.btpn.user.User;

/**
 * 
 * @author muslim
 * @category Discount Utiliy Class
 *
 */

@Service
public class Discount {
	
	/**
	 * Calculate Discount based on User Type
	 * @param user
	 * @return discount value in percentage
	 */
	public double discountByUserTypeOnPercentage(User user) {
		double discountVal = 0.0;
		
		switch (user.getUserType()) {
		case User.USER_TYPE_EMPLOYEE:
			discountVal = 30.0;
			break;

		case User.USER_TYPE_AFFILIATE:
			discountVal = 10.0;
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
			discountVal = 5.0;
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
	public double netPayableAmount(User user, double paidBills, boolean isGroceries) {
		double percentDiscount = 0.0;
		
		if (!isGroceries) {
			percentDiscount = discountByUserTypeOnPercentage(user);
			percentDiscount += discountByYearsNumOnPercentage(user);
		}
		double netAmount = percentDiscount > 0.0 ? paidBills - ((percentDiscount*paidBills)/100) : paidBills;

		return netAmount - discountByHundredBillsOnDollars(paidBills);
	}
	
	
	
}
