package com.mitrais.btpn.user.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mitrais.btpn.user.User;
import com.mitrais.btpn.user.service.UserService;

public class UserControllerTest {
	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userCont;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testReadFromJsonFile() {
		List<User> userList = UserService.readUserFromFile();
		List<User> result = userCont.getAllUserListFromJsonFile();
		assertThat(result.toString(), is(userList.toString()));
	}

}
