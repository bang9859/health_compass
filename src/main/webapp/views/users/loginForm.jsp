<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/usersForm.css">
<script type="module" src="/resources/script/users/validation-login.js"></script>
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<form id="form-login" method="POST" action="/service/users">
				<input type="hidden" name="command" value="login">
				<input id="username" name="username" type="text" placeholder="아이디 입력" autocomplete="username">
				<p class="error-msg" id="err-msg-id">아이디를 입력해주세요.</p>
				<input id="password" name="password" type="password" placeholder="비밀번호 입력" autocomplete="new-password">
				<p class="error-msg" id="err-msg-pw">비밀번호를 입력해주세요.</p>
				<input id="form-submit" type="submit">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>