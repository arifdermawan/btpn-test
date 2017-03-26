package com.mitrais.btpn.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.btpn.user.User;
import com.mitrais.btpn.user.service.UserService;


/**
 * 
 * @author Muslim
 *
 */

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	@RequestMapping(method = RequestMethod.GET, path = "/list")
	public @ResponseBody List<User> getAllUserListFromJsonFile() {
		List<User> userList = UserService.readUserFromFile();
		return userList;
	}
}
