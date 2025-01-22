window.addEventListener('load', function() {
	const path = location.pathname.substring(1); // 현재 경로에서 '/' 제거
	console.log("path : " + path);

	const titleElement = document.getElementById("title");
	if (path == "/") {
		titleElement.textContent = "메인 페이지";
	} else if (path == "detail") {
		titleElement.textContent = "진료과 상세 페이지";
	} else if (path == "login") {
		titleElement.textContent = "로그인";
	} else if (path == "join") {
		titleElement.textContent = "회원가입";
	} else if (path == "mypage") {
		titleElement.textContent = "마이 페이지";
	} else if (path == "delete") {
		titleElement.textContent = "회원탈퇴";
	} else if (path == "schedule") {
		titleElement.textContent = "일정";
	} else if (path == "bookmark") {
		titleElement.textContent = "북마크";
	} else if (path == "hospitals") {
		titleElement.textContent = "병원 목록 페이지";
	}
});