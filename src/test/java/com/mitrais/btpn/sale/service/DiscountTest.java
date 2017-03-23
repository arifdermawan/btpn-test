package com.mitrais.btpn.sale.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mitrais.btpn.user.User;
import com.mitrais.btpn.user.dao.UserDAO;


public class DiscountTest {
	
	@Mock
	UserDAO userDao;
	
	@InjectMocks
	private Discount discount;

	User employee;
	User affiliate;
	
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this);
        
    	try {
    		// instantiate user
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			employee = new User(1, "John Denver", User.USER_TYPE_EMPLOYEE, sdf.parse("2012-01-20"));
			affiliate = new User(2, "Bobby Brown", User.USER_TYPE_AFFILIATE,  sdf.parse("2016-11-20"));
			discount = new Discount(30, 10, 5, userDao);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void userTypeDiscountTest() {
		
		double empDisc = discount.discountByUserTypeOnPercentage(employee);
		assertTrue(empDisc == 30);
		
		double affDisc = discount.discountByUserTypeOnPercentage(affiliate);
		assertTrue(affDisc == 10);
	}
	
	@Test
	public void overTwoYearsDiscountTest() {
		
		double empYearDisc = discount.discountByYearsNumOnPercentage(employee);
		assertTrue(empYearDisc == 5.0);
		
		double affYearDisc = discount.discountByYearsNumOnPercentage(affiliate);
		assertTrue(affYearDisc == 0.0);
	}
	
	@Test
	public void discountByTheBillTest() {
		
		double discountBill = discount.discountByHundredBillsOnDollars(100);
		assertTrue(discountBill == 5);
	}
	
	@Test
	public void netPayableAmountTest() {
		when(userDao.findByUserId(anyInt())).thenReturn(employee, employee, affiliate, affiliate);
		
		double employeGroceriesNetAmount = discount.netPayableAmount(1, 990, true);
		assertTrue(employeGroceriesNetAmount == 945);
		
		double employeNonGroceriesNetAmount = discount.netPayableAmount(1, 990, false);
		assertTrue(employeNonGroceriesNetAmount == 598.5);
		
		
		double affiliateGroiceriesNetAmount = discount.netPayableAmount(2, 100, true);
		assertTrue(affiliateGroiceriesNetAmount == 95);
		
		double affiliateNonGroiceriesNetAmount = discount.netPayableAmount(2, 100, false);
		assertTrue(affiliateNonGroiceriesNetAmount == 85);
		
	}

}
