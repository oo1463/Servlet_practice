package com.ssafy.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.model.service.BookService;
import com.ssafy.model.service.BookServiceImpl;
import com.ssafy.model.service.UserService;
import com.ssafy.model.service.UserServiceImpl;

@WebServlet(value = "/main",loadOnStartup = 1)
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BookService bookService = new BookServiceImpl();
	UserService userService = new UserServiceImpl();
    
    public MainServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		ServletContext application = getServletContext();
		application.setAttribute("root", application.getContextPath());  // 서블릿 컨텍스트에 ContextPath로 "root"라는 이름으로 등록
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		
		
	}

}
