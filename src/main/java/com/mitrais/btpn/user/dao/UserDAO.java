package com.mitrais.btpn.user.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
