package com.mitrais.btpn.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.btpn.sale.service.Discount;

@RestController("/discount")
public class DiscountRestService {
	
	@Autowired
	Discount disc;

	@RequestMapping(method = RequestMethod.POST, path = "/netAmount")
	public Double getNetAmount(@PathVariable(name="userId") Integer userId, @PathVariable(name="userId") Double chargedBill, @PathVariable(name="userId") boolean isGroceries) {
		return disc.netPayableAmount(userId, chargedBill, isGroceries);
	}
	
}
