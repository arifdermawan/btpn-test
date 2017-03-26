package com.mitrais.btpn.user.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.btpn.user.User;
import com.mitrais.btpn.user.dao.UserDAO;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDao;
	
	public User findByUserId(Integer userId) {
		return userDao.findByUserId(userId);
	}
	
	public static List<User> readUserFromFile() {
		List<User> userList = new ArrayList<>();
		
		JSONParser jsonP = new JSONParser();
		
		try {
			JSONArray jsonArr = (JSONArray) jsonP.parse(new FileReader("src/main/resources/user.json"));
			
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject jsonObj = (JSONObject) jsonArr.get(i);
				User user = new User(
						String.valueOf(jsonObj.get("user_id")),
						(String) jsonObj.get("user_name"), (String) jsonObj.get("user_type"),
						(String) jsonObj.get("join_date"));
				userList.add(user);
			}
			
		} catch (IOException | ParseException | NumberFormatException e) {
			e.printStackTrace();
		} 
		
		return userList;
	};
}
