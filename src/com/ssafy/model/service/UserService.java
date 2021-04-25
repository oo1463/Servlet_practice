package com.ssafy.model.service;

import java.sql.SQLException;

import com.ssafy.model.dto.User;

public interface UserService {
	User select(String id, String pass) throws SQLException;
}
