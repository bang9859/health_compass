window.onload = () => {
	const path = location.pathname.substring(1); // 현재 경로에서 '/' 제거
	console.log("path : " + path);

	const titleElement = document.getElementById("title");
	if (path == "index.jsp") {
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

	const login = document.getElementById("login");
	const join = document.getElementById("join");
	const mypage = document.getElementById("mypage");
	const logout = document.getElementById("logout");

	login.addEventListener("click", e => {
		window.location.href = "/login";
	});

	join.addEventListener("click", e => {
		window.location.href = "/join";
	});

	mypage.addEventListener("click", e => {
		window.location.href = "/mypage";
	});

	logout.addEventListener("click", e => {
		logoutAction();
	});
};

async function logoutAction() {
	const response = await fetch("/service/users?command=logout", {
		method: "GET",
		headers: {
			"Content-Type": "application/json"
		}
	});
	await response.json();
	return true;
}
