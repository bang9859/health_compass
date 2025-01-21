document.addEventListener("DOMContentLoaded", function() {
	console.log("JavaScript 실행됨");

	if (typeof hospitalList === "undefined" || hospitalList.length === 0) {
		console.error("hospitalList가 정의되지 않았거나 비어 있습니다!");
		return;
	}

	const hospitalsContainer = document.getElementById("hospitals");

	// 마우스 휠 이벤트 처리
	hospitalsContainer.addEventListener("wheel", function(event) {
		event.preventDefault(); // 기본 스크롤 동작 방지
		hospitalsContainer.scrollTop += event.deltaY; // 휠 방향에 따라 스크롤
	});

	const pagingContainer = document.getElementById("paging");
	const mapContainer = document.getElementById("map");

	let userLat, userLon; // 사용자 위치 저장 변수

	navigator.geolocation.getCurrentPosition(
		function(position) {
			userLat = position.coords.latitude;
			userLon = position.coords.longitude;

			const mapOption = {
				center: new kakao.maps.LatLng(userLat, userLon),
				level: 3,
			};
			const map = new kakao.maps.Map(mapContainer, mapOption);
			const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

			let markers = [];

			// 내 위치 마커 생성
			const addUserLocationMarker = () => {
				const userPosition = new kakao.maps.LatLng(userLat, userLon);
				const userMarkerImage = new kakao.maps.MarkerImage(
					"/resources/imges/내위치마킹.png", // 내 위치 마커 이미지 경로
					new kakao.maps.Size(50, 50),
					{ offset: new kakao.maps.Point(25, 50) }
				);
				const userMarker = new kakao.maps.Marker({
					position: userPosition,
					image: userMarkerImage,
				});
				userMarker.setMap(map);

				// "현재 위치" 인포윈도우 항상 표시
				const userInfoWindow = new kakao.maps.InfoWindow({
					content: '<div style="padding:5px;">현재 위치</div>',
				});
				userInfoWindow.open(map, userMarker);
			};

			addUserLocationMarker(); // 내 위치 마커 초기 표시

			// 병원 마커 추가 함수
			const addHospitalMarker = (position, title) => {
				const hospitalMarkerImage = new kakao.maps.MarkerImage(
					"/resources/imges/병원위치마킹.png", // 병원 마커 이미지 경로
					new kakao.maps.Size(36, 37),
					{ offset: new kakao.maps.Point(18, 37) }
				);
				const marker = new kakao.maps.Marker({
					position,
					title,
					image: hospitalMarkerImage,
				});
				marker.setMap(map);
				markers.push(marker);

				return marker;
			};

			// 병원 목록 렌더링
			const renderHospitals = (page) => {
				const start = (page - 1) * 10;
				const end = Math.min(start + 10, hospitalList.length);

				console.log(`Rendering hospitals for page ${page}: start=${start}, end=${end}`);

				hospitalsContainer.innerHTML = "";
				markers.forEach((marker) => marker.setMap(null)); // 기존 병원 마커 제거
				markers = []; // 마커 배열 초기화

				for (let i = start; i < end; i++) {
					const hospital = hospitalList[i];
					const hospitalElement = document.createElement("div");
					hospitalElement.className = "hospital";
					hospitalElement.innerHTML = `
						<p id="hospitalNumber"><strong>${i + 1} 번</strong></p>
                        <p><strong>병원이름:</strong> ${hospital.name}</p>
						<p><strong>분류:</strong> ${hospital.type || "정보 없음"}</p>
						<p><strong>응급실 여부:</strong> ${hospital.emergency === "1" ? "운영 중" : "미운영"}</p>
						<p><strong>주소:</strong> ${hospital.address}</p>
                        <p><strong>전화번호:</strong> ${hospital.phone || "휴진"}</p>
                        <label for="operatingHours-${i}"><strong>진료시간:</strong></label>
                        <select id="operatingHours-${i}" class="operating-hours">
                            ${hospital.operatingHours
							.split("\n")
							.map((time) => `<option>${time || "정보 없음"}</option>`)
							.join("")}
                        </select>
                        <button class="bookmark-but">북마크 추가하기</button>
                    `;

					hospitalsContainer.appendChild(hospitalElement);

					const markerPosition = new kakao.maps.LatLng(
						hospital.latitude,
						hospital.longitude
					);

					const marker = addHospitalMarker(markerPosition, hospital.name);

					hospitalElement.onmouseover = () => {
						infowindow.setContent(`<div style="padding:5px;">${hospital.name}</div>`);
						infowindow.open(map, marker);
					};

					hospitalElement.onmouseout = () => {
						infowindow.close();
					};

					hospitalElement.onclick = () => {
						map.panTo(markerPosition);
					};
				}
			};

			// 페이징 생성
			const createPagination = () => {
				const totalPages = Math.ceil(hospitalList.length / 10);
				pagingContainer.innerHTML = "";

				for (let i = 1; i <= totalPages; i++) {
					const pageButton = document.createElement("button");
					pageButton.textContent = i;
					pageButton.className = "page-button";
					pageButton.addEventListener("click", () => renderHospitals(i));
					pagingContainer.appendChild(pageButton);
				}

				// 현재 위치로 이동 버튼 추가
				const goToCurrentLocationButton = document.createElement("button");
				goToCurrentLocationButton.textContent = "현재 위치로 이동";
				goToCurrentLocationButton.className = "current-location-button";
				goToCurrentLocationButton.addEventListener("click", () => {
					map.panTo(new kakao.maps.LatLng(userLat, userLon));
				});
				pagingContainer.appendChild(goToCurrentLocationButton);
			};

			createPagination();
			renderHospitals(1);
		},
		function(error) {
			console.error("사용자 위치를 가져올 수 없습니다:", error);
		}
	);
});
