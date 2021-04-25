package com.ssafy.model.service;

import java.sql.SQLException;

import com.ssafy.model.dao.UserDao;
import com.ssafy.model.dao.UserDaoImpl;
import com.ssafy.model.dto.User;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	
	@Override
	public User select(String id, String pass) throws SQLException {
		return userDao.select(id, pass);
	}

}
