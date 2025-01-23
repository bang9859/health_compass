<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link rel="stylesheet" href="/resources/style/globals.css">
<link rel="stylesheet" href="/resources/style/usersForm.css">
<script type="module" src="/resources/script/users/validation-update.js"></script>
</head>
<c:import url="/header" />
<body>
<c:if test="${empty log}">
	<c:redirect url="/login" />
</c:if>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<form id="form-update" method="POST" action="/service/users">
			    <input type="hidden" name="command" value="update">
			    <label for="username">아이디:</label>
			    <input type="text" value="${log.username}" disabled>
			    <input id="username" name="username" type="hidden" value="${log.username}">
			    <label for="password">비밀번호:</label>
			    <input id="password" name="password" type="password" autocomplete="new-password">
			    <p class="error-msg" id="err-msg-pw">사용할 수 없는 비밀번호입니다.</p>
			    <label for="email">이메일:</label>
			    <input id="email" name="email" type="email" value="${log.email}">
			    <p class="error-msg" id="err-msg-email">이메일 형식이 올바르지 않습니다.</p>
			    <p class="error-msg" id="err-msg-duplEmail">이메일이 중복됩니다.</p>
			    <label for="name">이름:</label>
			    <input id="name" name="name" type="text" value="${log.name}" disabled>
			    <label for="birth">생년월일:</label>
			    <input id="birth" name="birth" type="date" value="${log.birth}" disabled>
			    <label>성별:</label>
			    <div id="select-gender">
			        <div class="form_radio_btn">
			            <input id="male" type="radio" name="gender" value="male" ${log.gender == 'male' ? 'checked' : ''} disabled>
			            <label for="male">남자</label>
			        </div>
			        <div class="form_radio_btn">
			            <input id="female" type="radio" name="gender" value="female" ${log.gender == 'female' ? 'checked' : ''} disabled>
			            <label for="female">여자</label>
			        </div>
			    </div>
			    <label for="tel">전화번호:</label>
			    <input id="tel" name="tel" type="text" value="${log.tel}">
			    <p class="error-msg" id="err-msg-tel">잘못된 입력입니다.</p>
			    <p class="error-msg" id="err-msg-duplTel">전화번호가 중복됩니다.</p>
				<div id="button-container">
				    <button id="form-submit" type="submit">회원정보수정</button>
				    <button id="delete-button" type="button" onclick="location.href='/delete'">회원탈퇴</button>
				</div>
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>