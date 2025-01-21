<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/usersForm.css">
<script type="module" src="/resources/script/users/validation-join.js"></script>
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<form id="form-join" method="POST" action="/service/users">
				<input type="hidden" name="command" value="join">
				<input id="username" name="username" type="text" placeholder="아이디 입력" autocomplete="username">
				<p class="error-msg" id="err-msg-id">사용할 수 없는 아이디입니다.</p>
				<p class="error-msg" id="err-msg-duplId">아이디가 중복됩니다.</p>
				<input id="password" name="password" type="password" placeholder="비밀번호 입력" autocomplete="new-password">
				<p class="error-msg" id="err-msg-pw">사용할 수 없는 비밀번호입니다.</p>
				<input id="email" name="email" type="email" placeholder="이메일 입력">
				<p class="error-msg" id="err-msg-email">이메일 형식이 올바르지 않습니다.</p>
				<p class="error-msg" id="err-msg-duplEmail">이메일이 중복됩니다.</p>
				<input id="name" name="name" type="text" placeholder="이름 입력">
				<input id="birth" name="birth" type="date">
				<p class="error-msg" id="err-msg-birth">생년월일을 입력해주세요.</p>
				<div id="select-box">
					<span>성별</span>
					<div id="select-gender">
						<div class="form_radio_btn">
							<input id="male" type="radio" name="gender" value="male" checked>
							<label for="male">남자</label>
						</div>
						<div class="form_radio_btn">
							<input id="female" type="radio" name="gender" value="female">
							<label for="female">여자</label>
						</div>
					</div>
				</div>
				<input id="tel" name="tel" type="text" placeholder="전화번호 입력">
				<p class="error-msg" id="err-msg-tel">잘못된 입력입니다.</p>
				<p class="error-msg" id="err-msg-duplTel">전화번호가 중복됩니다.</p>
				<input id="form-submit" type="submit">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>