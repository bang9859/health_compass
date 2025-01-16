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
			<form id="form-join" method="POST" action="/service/users">
				<input type="hidden" name="command" value="join">
				<input id="username" name="username" type="text" placeholder="아이디 입력">
				<input id="password" name="password" type="password" placeholder="비밀번호 입력">
				<input id="email" name="email" type="email" placeholder="이메일 입력">
				<input id="name" name="name" type="text" placeholder="이름 입력">
				<input id="birth" name="birth" type="date">
				<div id="select-gender">
					<input type="radio" name="gender" value="남자"><lable for="male">남자</lable>
					<input type="radio" name="gender" value="여자"><lable for="female">여자</lable>
				</div>
				<input id="tel" name="tel" type="text" placeholder="전화번호 입력">
				<input id="submit" type="submit">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>