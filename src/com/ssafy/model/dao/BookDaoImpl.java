package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.dto.Book;
import com.ssafy.util.DBUtil;

public class BookDaoImpl implements BookDao {
	
	DBUtil dbUtil = DBUtil.getUtil();

	@Override
	public void insert(Book product) throws SQLException {
		Connection conn = dbUtil.getConnection();
		String sql = "insert into book(isbn, title, author, price) values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
				
		ps.setString(1, product.getIsbn());
		ps.setString(2, product.getTitle());
		ps.setString(3, product.getAuthor());
		ps.setInt(4, product.getPrice());
		ps.executeUpdate();
		
		dbUtil.close(conn, ps);
	}

	@Override
	public Book select(String pCode) throws SQLException {
		// 1. DB 연결, resultSet, ps,  sql 작성
		
		Connection conn = dbUtil.getConnection();
		ResultSet rs = null;
		String sql = "select isbn, title, author, price from book where isbn = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setNString(1, pCode);
		rs = ps.executeQuery();
		Book tmpBook = null;
		if(rs.next()) {
			tmpBook = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		}
		dbUtil.close(conn, rs, ps);
		
		return tmpBook;
	}

	@Override
	public List<Book> select() throws SQLException {  // Search all books and return ArrayList
		Connection conn = dbUtil.getConnection();
		ResultSet rs = null;
		String sql = "select isbn, title, author, price from book";
		PreparedStatement ps = conn.prepareStatement(sql);
				
		rs = ps.executeQuery();
		ArrayList<Book> bookList = new ArrayList<>();		
		while(rs.next()) {
			bookList.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		
		dbUtil.close(conn, rs, ps);
		
		return bookList;
	}

}
