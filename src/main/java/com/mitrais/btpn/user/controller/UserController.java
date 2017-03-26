package com.mitrais.btpn.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.btpn.user.User;
import com.mitrais.btpn.user.service.UserService;

@RestController("/user")
public class UserController {
	
	@RequestMapping("/list")
	public @ResponseBody List<User> getAllUserListFromJsonFile() {
		return UserService.readUserFromFile();
	}
}
