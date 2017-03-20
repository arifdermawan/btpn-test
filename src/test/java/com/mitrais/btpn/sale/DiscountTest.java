package com.mitrais.btpn.sale;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.mitrais.btpn.user.User;

public class DiscountTest {
	
	@InjectMocks
	private Discount disc;

	User employee;
	User affiliate;
	
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this);
        
    	try {
    		// instantiate user
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			employee = new User(User.USER_TYPE_EMPLOYEE, sdf.parse("2012-01-20"));
			affiliate = new User(User.USER_TYPE_AFFILIATE, sdf.parse("2016-11-20"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void userTypeDiscountTest() {
		
		double empDisc = disc.discountByUserTypeOnPercentage(employee);
		assertTrue(empDisc == 30);
		
		double affDisc = disc.discountByUserTypeOnPercentage(affiliate);
		assertTrue(affDisc == 10);
	}
	
	@Test
	public void overTwoYearsDiscountTest() {
		
		double empYearDisc = disc.discountByYearsNumOnPercentage(employee);
		assertTrue(empYearDisc == 5.0);
		
		double affYearDisc = disc.discountByYearsNumOnPercentage(affiliate);
		assertTrue(affYearDisc == 0.0);
	}
	
	@Test
	public void discountByTheBillTest() {
		
		double discountBill = disc.discountByHundredBillsOnDollars(100);
		assertTrue(discountBill == 5);
	}
	
	@Test
	public void netPayableAmountTest() {
		
		double employeGroceriesNetAmount = disc.netPayableAmount(employee, 990, true);
		assertTrue(employeGroceriesNetAmount == 945);
		
		double employeNonGroceriesNetAmount = disc.netPayableAmount(employee, 990, false);
		assertTrue(employeNonGroceriesNetAmount == 598.5);
		
		double affiliateGroiceriesNetAmount = disc.netPayableAmount(affiliate, 100, true);
		assertTrue(affiliateGroiceriesNetAmount == 95);
		
		double affiliateNonGroiceriesNetAmount = disc.netPayableAmount(affiliate, 100, false);
		assertTrue(affiliateNonGroiceriesNetAmount == 85);
		
	}

}
