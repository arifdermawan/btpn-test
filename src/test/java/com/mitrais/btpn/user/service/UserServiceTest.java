package com.mitrais.btpn.user.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.mitrais.btpn.user.User;

public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetUserListFromJSONFile() {
		List<User> result = userService.readUserFromFile(); 
		System.out.println(result);
		assertThat(result.get(0).getUserId(), is(1));
		assertThat(result.get(1).getUserId(), is(2));
	}

}
 