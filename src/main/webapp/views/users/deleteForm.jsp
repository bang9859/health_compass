<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/usersForm.css">
<script type="module" src="/resources/script/users/validation-delete.js"></script>
</head>
<c:import url="/header" />
<body>
<c:if test="${empty log}">
	<c:redirect url="/login" />
</c:if>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<form id="form-delete" method="POST" action="/service/users">
				<input type="hidden" name="command" value="delete">
				<label for="username">아이디:</label>
				<input type="text" value="${log.username}" disabled>
				<input id="username" name="username" type="hidden" value="${log.username}" disabled>
				<label for="password">비밀번호:</label>
				<input id="password" name="password" type="password" autocomplete="new-password">
				<p class="error-msg" id="err-msg-pw">비밀번호를 입력해주세요.</p>
				<input id="form-submit" type="submit" value="회원탈퇴">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>