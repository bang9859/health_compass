document.addEventListener("DOMContentLoaded", function() {
	const calendarDate = document.getElementById("calendar-date");
	const calendarMonth = document.getElementById("calendar-month");
	const btnPrev = document.getElementById("btnPrev");
	const btnNext = document.getElementById("btnNext");

	let currentDate = new Date();

	// 달력을 생성하는 함수
	function renderCalendar(date) {
		const year = date.getFullYear();
		const month = date.getMonth(); // 0부터 시작 (0: 1월, 1: 2월, ...)
		const firstDayOfMonth = new Date(year, month, 1); // 이번 달의 첫 번째 날
		const lastDayOfMonth = new Date(year, month + 1, 0); // 이번 달의 마지막 날

		// 달 이름 업데이트
		const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
		calendarMonth.textContent = monthNames[month];

		// 날짜를 초기화
		calendarDate.innerHTML = "";

		// 첫째 주를 채우기 위해 빈 칸 추가
		let startDay = firstDayOfMonth.getDay(); // 0: 일요일, 1: 월요일, ...
		let row = document.createElement("tr");
		for (let i = 0; i < startDay; i++) {
			const cell = document.createElement("td");
			row.appendChild(cell);
		}

		// 날짜 채우기
		for (let day = 1; day <= lastDayOfMonth.getDate(); day++) {
			const cell = document.createElement("td");
			cell.textContent = day;
			row.appendChild(cell);

			// 한 주(7일)가 끝나면 새로운 행 추가
			if ((startDay + day) % 7 === 0) {
				calendarDate.appendChild(row);
				row = document.createElement("tr");
			}
		}

		// 마지막 줄을 채우기 위해 빈 칸 추가
		if (row.children.length > 0) {
			while (row.children.length < 7) {
				const cell = document.createElement("td");
				row.appendChild(cell);
			}
			calendarDate.appendChild(row);
		}
	}

	// 이전 달 버튼 클릭 이벤트
	btnPrev.addEventListener("click", function() {
		currentDate.setMonth(currentDate.getMonth() - 1);
		renderCalendar(currentDate);
	});

	// 다음 달 버튼 클릭 이벤트
	btnNext.addEventListener("click", function() {
		currentDate.setMonth(currentDate.getMonth() + 1);
		renderCalendar(currentDate);
	});

	// 초기 달력 렌더링
	renderCalendar(currentDate);

});