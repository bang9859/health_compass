<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/usersForm.css">
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<form id="form-join" method="POST" action="/users/join">
				<input id="username" name="username" type="text">
				<input id="password" name="password" type="password">
				<input id="email" name="email" type="email">
				<input id="name" name="name" type="text">
				<input id="birth" name="birth" type="date">
				<input type="checkbox" value="남자">
				<input type="checkbox" value="여자">
				<input id="tel" name="tel" type="text">
				<input id="submit" type="submit">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>