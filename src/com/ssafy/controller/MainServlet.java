package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.model.dto.Book;
import com.ssafy.model.dto.User;
import com.ssafy.model.service.BookService;
import com.ssafy.model.service.BookServiceImpl;
import com.ssafy.model.service.UserService;
import com.ssafy.model.service.UserServiceImpl;
import com.ssafy.util.PageInfo;

@WebServlet(value = "/main",loadOnStartup = 1)
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BookService bookService = new BookServiceImpl();
	UserService userService = new UserServiceImpl();
    
    public MainServlet() {
        super();
    }

	public void init() throws ServletException {
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
		try {	
			PageInfo pageInfo = null;
			if(act.equals("registForm")) {
				pageInfo = registForm(request,response);
			}
			else if(act.equals("regist")) {
				pageInfo = regist(request,response);
			}
			else if(act.equals("login")) {
				pageInfo = login(request,response);
			}
			else if(act.equals("logout")) {
				pageInfo = logout(request,response);
			}
			else if(act.equals("list")) {
				pageInfo = list(request,response);
			}
			
			if(pageInfo.isForward()) {  // forwarding일 시 requestDispatcher forward
				request.getRequestDispatcher(pageInfo.getUrl()).forward(request, response);
			}else {  // redirect
				response.sendRedirect(request.getContextPath() + pageInfo.getUrl());
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// 500.jsp로 이동만 하면 되는 경우
			response.sendRedirect(request.getContextPath()+"/error/500.jsp");
			// 500.jsp에서 예외 메시지를 출력해야하는 경우
//			request.setAttribute("errorMsg", e.getMessage());
//			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
		}
		
	}
	
	
	protected PageInfo registForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			return new PageInfo("/regist.jsp", true);
		}
		else {
			return new PageInfo("/index.jsp", false);
		}
		
	}
	
	protected PageInfo regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		bookService.insert(new Book(isbn, title, author, price));
		return new PageInfo("/index.jsp", false);
	}
	
	protected PageInfo login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		User user = userService.select(id, pass);
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("msg", "id, password를 확인하세요");
		}
		return new PageInfo("/index.jsp", true);
		
	}
	
	protected PageInfo logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return new PageInfo("/index.jsp", false);
	}
	
	protected PageInfo list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		request.setAttribute("books", bookService.select());		
		return new PageInfo("/list.jsp", true);
	}

}
