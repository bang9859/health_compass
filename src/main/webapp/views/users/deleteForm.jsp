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
			<form id="form-delete" method="POST" action="/users/delete">
				<input id="username" name="username" type="text" disabled>
				<input id="password" name="password" type="password">
				<input type="submit">
			</form>
		</section>
	</div>
</body>
<c:import url="/footer" />
</html>