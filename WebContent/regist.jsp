<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#registForm>label {
	display: inline-block;
	width: 100px;
}
</style>
</head>
<body>
	<%@ include file="/include/header.jsp"%>
	<h2>도서 등록</h2>
	<form method="post" action="${root }/main" id="registForm">
		isbn : <input type="text" name ="isbn"> <br/>
		title : <input type="text" name ="title"> <br/>
		author : <input type="text" name ="author"> <br/>
		price : <input type="number" name ="price"> <br/>
		<input type="hidden" name="act" value="regist">
		<input type="submit" value="도서 등록" id="regist">
	</form>
</body>
<script>

</script>
</html>
