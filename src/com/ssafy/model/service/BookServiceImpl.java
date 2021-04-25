package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.model.dao.BookDao;
import com.ssafy.model.dao.BookDaoImpl;
import com.ssafy.model.dto.Book;

public class BookServiceImpl implements BookService {
	
	BookDao bookDao = new BookDaoImpl();

	@Override
	public void insert(Book product) throws SQLException {
		bookDao.insert(product);
	}

	@Override
	public Book select(String pCode) throws SQLException {
		return bookDao.select(pCode);
	}

	@Override
	public List<Book> select() throws SQLException {
		return bookDao.select();
	}
}
