package com.mitrais.btpn.error.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class applicationErrorController implements ErrorController {
	
	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "This is an Error handling";
    }

	@Override
	public String getErrorPath() {
		return this.PATH;
	}

	
}
