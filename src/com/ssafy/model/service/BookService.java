package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.model.dto.Book;

public interface BookService {
	void insert(Book product) throws SQLException;

	Book select(String pCode) throws SQLException;

	List<Book> select() throws SQLException;
}
