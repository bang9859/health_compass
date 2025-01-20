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

	// 기존 window.onload 코드를 여기에 작성
	const addForm = document.querySelector(".schedule-add-container");
	const name = document.getElementById("medicine-name");
	const startDate = document.getElementById("start-date");
	const endDate = document.getElementById("end-date");
	const number = document.getElementById("daily-frequency");

	let isValidNumber = validateNumber(number.value);
	let isValidStartDate = validateStartDate(startDate.value, endDate.value);

	name.addEventListener("change", (e) => {
		const input = e.target.value;
		const errEmpty = document.getElementById("error-msg-name-empty");
		if (input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
	});
	startDate.addEventListener("change", (e) => {
		const input = e.target.value;
		const errEmpty = document.getElementById("error-msg-start-date-empty");
		const endErrEmpty = document.getElementById("error-msg-end-date-empty");
		const errPattern = document.getElementById("error-msg-start-date-pattern");

		// 종료 날짜 입력 여부 확인
		if (!endDate.value) {
			updateErrorElementStyle(endErrEmpty, true);
		} else {
			updateErrorElementStyle(endErrEmpty, false);
		}

		// 시작 날짜 입력 여부 확인
		if (!input) {
			updateErrorElementStyle(errEmpty, true);
			updateErrorElementStyle(errPattern, false); // 패턴 오류는 비활성화
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}

		// 종료 날짜와 함께 유효성 검사
		if (endDate.value) {
			isValidStartDate = validateStartDate(input, endDate.value);
			if (!isValidStartDate) {
				updateErrorElementStyle(errPattern, true);
			} else {
				updateErrorElementStyle(errPattern, false);
			}
		}
	});

	endDate.addEventListener("change", (e) => {
		const input = e.target.value;
		const errEmpty = document.getElementById("error-msg-end-date-empty");
		const startErrEmpty = document.getElementById("error-msg-start-date-empty");
		const errPattern = document.getElementById("error-msg-start-date-pattern");

		// 시작 날짜 입력 여부 확인
		if (!startDate.value) {
			updateErrorElementStyle(startErrEmpty, true);
		} else {
			updateErrorElementStyle(startErrEmpty, false);
		}

		// 종료 날짜 입력 여부 확인
		if (!input) {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}

		// 시작 날짜와 함께 유효성 검사
		if (startDate.value) {
			isValidStartDate = validateStartDate(startDate.value, input);
			if (!isValidStartDate) {
				updateErrorElementStyle(errPattern, true);
			} else {
				updateErrorElementStyle(errPattern, false);
			}
		}
	});


	number.addEventListener("change", (e) => {
		const input = e.target.value;
		const errEmpty = document.getElementById("error-msg-number-empty");
		const errPattern = document.getElementById("error-msg-number-pattern");

		if (input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}

		isValidNumber = validateNumber(input);

		if (!isValidNumber) {
			updateErrorElementStyle(errPattern, true);
		} else {
			updateErrorElementStyle(errPattern, false);
		}
	});

	addForm.addEventListener("submit", (e) => {
		e.preventDefault();

		if (name.value === "") {
			const error = document.getElementById("error-msg-name-empty");
			updateErrorElementStyle(error, true);
		}

		if (!isValidStartDate && startDate.value === "") {
			const error = document.getElementById("error-msg-start-date-empty");
			updateErrorElementStyle(error, true);
		}

		if (endDate.value === "") {
			const error = document.getElementById("error-msg-end-date-empty");
			updateErrorElementStyle(error, true);
		}

		if (!isValidNumber && number.value === "") {
			const error = document.getElementById("error-msg-number-empty");
			updateErrorElementStyle(error, true);
		}

		if (isValidNumber && isValidStartDate) {
			addForm.submit();
		}
	});
	function updateErrorElementStyle(element, visible) {
		if (visible) {
			element.style.display = "block";
		} else {
			element.style.display = "none";
		}
	}

	function validateNumber(number) {
		const regex = /^[1-5]{1}$/;
		return regex.test(number);
	}

	function validateStartDate(startDate, endDate) {
		const start = new Date(startDate);
		const end = new Date(endDate);
		return start <= end;
	}

	document.getElementById("submit-button").addEventListener("click", async () => {
	        // 1. 입력값 가져오기
	        const medicineName = document.getElementById("medicine-name")?.value.trim() || "";
	        const startDate = document.getElementById("start-date")?.value || "";
	        const endDate = document.getElementById("end-date")?.value || "";
	        const dailyFrequency = document.getElementById("daily-frequency")?.value || "";

	        if (!medicineName || !startDate || !endDate || !dailyFrequency) {
	            alert("모든 필드를 올바르게 입력해주세요.");
	            return;
	        }

	        try {
	            // 2. API 호출
	            const apiUrl = `http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey=your-service-key&itemName=${encodeURIComponent(medicineName)}`;
	            const response = await fetch(apiUrl);

	            if (!response.ok) {
	                throw new Error("API 호출 실패");
	            }

	            const data = await response.json();
	            console.log("Fetched Data:", data);

	            if (!data?.body?.items?.length) {
	                alert("약 정보를 찾을 수 없습니다.");
	                return;
	            }

	            const medicineCode = data.body.items[0].itemSeq;

	            // 3. 서버로 데이터 전송
	            const saveResponse = await fetch("/AddScheduleAction", {
	                method: "POST",
	                headers: {
	                    "Content-Type": "application/x-www-form-urlencoded"
	                },
	                body: new URLSearchParams({
	                    "medicine-name": medicineName,
	                    "medicine-code": medicineCode,
	                    "start-date": startDate,
	                    "end-date": endDate,
	                    "daily-frequency": dailyFrequency
	                })
	            });

	            if (!saveResponse.ok) {
	                throw new Error("일정 저장 실패");
	            }

	            alert("일정이 성공적으로 등록되었습니다!");
	            window.location.href = "/schedule";
	        } catch (error) {
	            console.error(error);
	            alert("오류가 발생했습니다. 다시 시도해주세요.");
	        }
	    });
	
});






