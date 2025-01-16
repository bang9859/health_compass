<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/usersForm.css">
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<form id="form-login" method="POST" action="/service/users">
				<input type="hidden" name="command" value="login">
				<input id="username" name="username" type="text">
				<input id="password" name="password" type="password">
				<input type="submit">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>