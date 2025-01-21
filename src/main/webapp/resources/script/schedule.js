document.addEventListener("DOMContentLoaded", function() {

	const addForm = document.querySelector(".schedule-add-container");
	const code = document.getElementById("medicine-code");
	const startDate = document.getElementById("start-date");
	const endDate = document.getElementById("end-date");
	const number = document.getElementById("daily-frequency");

	let isValidNumber = validateNumber(number.value);
	let isValidStartDate = validateStartDate(startDate.value, endDate.value);

	code.addEventListener("change", (e) => {
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

		if (code.value === "") {
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
// 모달 열기 및 닫기
function toggleMedicineSearchModal() {
	const modal = document.getElementById('medicine-search-modal');
	if (modal.style.display === 'none' || modal.style.display === '') {
		modal.style.display = 'block';
	} else {
		modal.style.display = 'none';
	}
}

// 약품 검색 기능 (버튼 클릭 시 호출)


function displayMedicineResults(data) {
	const resultList = document.getElementById('medicine-result-list');
	resultList.innerHTML = '';  // 기존 검색 결과를 지우기

	if (data && data.body && data.body.items) {
		const items = data.body.items;
		items.forEach(item => {
			const li = document.createElement('li');
			li.textContent = item.itemName;  // 약품명 표시
			li.onclick = () => selectMedicine(item);  // 약품 선택 시 처리
			resultList.appendChild(li);
		});
	} else {
		resultList.innerHTML = '검색된 결과가 없습니다.';
	}
}

function searchMedicine() {
	const searchInput = document.getElementById('search-medicine');
	const drugName = searchInput.value;
	const serviceKey = 'oruvbo%2BL%2B8mY49TbDDPKgBJmt8%2BaC4EPCinp%2FKfYxFIgRIp7iRMVQoqyWxZle%2FBv%2B22H%2BLJTKBTKU02ylL3ZJg%3D%3D';

	const url = `http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey=${serviceKey}&itemName=${encodeURIComponent(drugName)}`;

	fetch(url)
		.then(response => response.text())  // 응답을 텍스트로 받기 (XML 형식일 경우)
		.then(xmlText => {
			// XML 파싱하기
			const parser = new DOMParser();
			const xmlDoc = parser.parseFromString(xmlText, "application/xml");

			// XML에서 필요한 데이터 추출 (예시: <itemName> 태그)
			const items = xmlDoc.getElementsByTagName("item");

			const resultList = document.getElementById('medicine-result-list');
			resultList.innerHTML = '';  // 기존 검색 결과를 지우기

			if (items.length > 0) {
				for (let i = 0; i < items.length; i++) {
					const itemName = items[i].getElementsByTagName("itemName")[0].textContent;
					const itemCode = items[i].getElementsByTagName("itemSeq")[0].textContent;

					const li = document.createElement('li');
					li.textContent = itemName;
					li.onclick = () => selectMedicine(itemCode, itemName);  // 선택 시 처리
					console.log("itemCode = " + itemCode, itemName);
					resultList.appendChild(li);
				}
			} else {
				resultList.innerHTML = '검색된 결과가 없습니다.';
			}
		})
		.catch(error => {
			console.error('Error fetching medicine data:', error);
			alert('약 정보를 가져오는 데 실패했습니다. 다시 시도해주세요.');
		});
}


function toggleMedicineSearchModal() {
	const modal = document.getElementById('medicine-search-modal');
	modal.style.display = modal.style.display === 'block' ? 'none' : 'block';  // 모달 열기/닫기
}

function selectMedicine(itemCode, itemName) {
	// 약품 이름과 번호를 UI에 반영
	document.getElementById('selected-medicine').textContent = itemName;
	document.getElementById('medicine-code').value = itemCode; // 숨겨진 필드에 코드 저장
	console.log("선택된 약품 번호:", itemCode); // 디버깅 로그 추가
	toggleMedicineSearchModal(); // 모달 닫기
}