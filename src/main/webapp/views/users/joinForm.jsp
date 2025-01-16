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
				<label for="userSex" class="form-label">성별</label>
				<div id="select-gender">
					<div class="form_radio_btn radio_male">
						<input id="radio-1" type="radio" name="userSex" value="male" checked>
						<label for="radio-1">남자</label>
					</div>
												 
					<div class="form_radio_btn">
						<input id="radio-2" type="radio" name="userSex" value="female">
						<label for="radio-2">여자</label>
					</div>
				</div>
				<input id="tel" name="tel" type="text" placeholder="전화번호 입력">
				<input id="submit" type="submit">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>