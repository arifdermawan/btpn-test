package com.mitrais.btpn.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.mitrais.btpn.user.User;

public interface UserDAO extends CrudRepository<User, Integer> {
	
	public User findByUserId(Integer userId);
	
}
