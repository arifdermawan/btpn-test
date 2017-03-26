package com.mitrais.btpn.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.btpn.sale.service.Discount;

/**
 * 
 * @author Muslim
 *
 */

@RestController
@RequestMapping(path="/discount")
public class DiscountRestService {
	
	@Autowired
	Discount disc;

	@RequestMapping(method = RequestMethod.POST, path = "/{userId}/netAmount/{chargedBill}/{isGroceries}")
	public Double getNetAmount(@PathVariable(value="userId") Integer userId, @PathVariable(value="chargedBill") Double chargedBill, @RequestBody @Validated@PathVariable(value="isGroceries") boolean isGroceries) {
		return disc.netPayableAmount(userId, chargedBill, isGroceries);
	}
	
}
