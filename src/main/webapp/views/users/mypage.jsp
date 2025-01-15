<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:import url="/header" />
<body>
	<div id="content-box">
		<c:import url="/nav" />
		<section id="main">
			<form id="form-join" method="POST" action="/users/join">
				<input id="username" name="username" type="text" disabled>
				<input id="password" name="password" type="password">
				<input id="email" name="email" type="email">
				<input id="name" name="name" type="text">
				<input id="birth" name="birth" type="date" disabled>
				<input type="checkbox" value="남자" disabled>
				<input type="checkbox" value="여자" disabled>
				<input id="tel" name="tel" type="text">
				<input type="submit">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>