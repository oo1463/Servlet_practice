package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ssafy.model.dto.User;
import com.ssafy.util.DBUtil;

public class UserDaoImpl implements UserDao {
	
	DBUtil dbUtil = DBUtil.getUtil();
	
	@Override
	public User select(String id, String pass) throws SQLException {
		Connection conn = dbUtil.getConnection();
		ResultSet rs = null;
		String sql = "select name from user where id=? and pass=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, id);
		ps.setString(2, pass);
		rs = ps.executeQuery();
		
		User user = null;
		
		if(rs.next()) {
			String userName = rs.getString(1);
			user = new User(id, pass, userName);
		}
		
		dbUtil.close(conn, rs, ps);
		
		return user;
	}

}
