<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/include/header.jsp" %>
	<h2>도서 목록</h2>
	<table>
		<thead>
			<tr>
				<th>도서 isbn</th>				
				<th>도서 이름</th>				
				<th>도서 가격</th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${books}" var="books">
				<tr>
					<td>${books.isbn}</td>				
					<td>${books.title}</td>				
					<td>${books.price}</td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>