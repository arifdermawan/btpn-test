package com.mitrais.btpn.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mitrais.btpn.user.User;

/**
 * 
 * @author muslim
 *
 */

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {
	
	public User findByUserId(Integer userId);
	
}
