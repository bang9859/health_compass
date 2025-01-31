//<a href="https://www.flaticon.com/kr/free-icons/" title="병원 아이콘">병원 아이콘 제작자: Freepik - Flaticon</a>
window.addEventListener("load", function() {
    // 지도 초기화
	initializeMap();
});

// 지도 초기화 함수 (Promise 기반)
function initializeMap() {
	return new Promise((resolve, reject) => {
		navigator.geolocation.getCurrentPosition(
			function (position) {
				const userLat = position.coords.latitude;
				const userLon = position.coords.longitude;

				const mapContainer = document.getElementById('map'); // 지도를 표시할 div
				const mapOption = {
					center: new kakao.maps.LatLng(userLat, userLon), // 지도의 중심좌표
					level: 3, // 지도의 확대 레벨
				};

				// 지도를 생성하고 resolve로 반환
				const map = new kakao.maps.Map(mapContainer, mapOption);
				resolve(map);
			},
			function (error) {
				// 위치 정보 가져오기 실패 시 reject 호출
				reject(`위치 정보를 가져오지 못했습니다. 에러 코드: ${error.code}`);
			}
		);
	});
}

// 주소로 좌표를 변환하여 지도를 이동하는 함수
function moveToAddress(address, name, map) {
    const geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(address, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            const marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

			// 인포윈도우에 표시할 HTML 콘텐츠
			const infowindowContent = `
			    <div style="
			        border-radius: 8px; 
					border: 1px solid black;
			        overflow: hidden; 
			        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
			        font-family: Arial, sans-serif; 
			        max-width: 250px;">
			        <div style="
			            background-color: #2C3E50; 
			            color: white; 
			            text-align: center; 
			            padding: 8px 12px; 
			            font-size: 16px; 
			            font-weight: bold;">
			            ${name}
			        </div>
			        <div style="
			            padding: 10px; 
			            font-size: 14px; 
			            line-height: 1.5; 
			            color: #333;">
			            <p style="margin: 0 0 8px;"><strong>주소:</strong> ${address}</p>
			        </div>
			    </div>
			`;

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			const infowindow = new kakao.maps.InfoWindow({
			    content: infowindowContent
			});
			infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
			map.setLevel(1, { animate: true });
        }
    });
}

// 북마크 클릭 처리
async function handleBookmarkClick(address, name) {
	alert(`병원 주소: ${address}`);
	
	let map = await initializeMap();
	moveToAddress(address, name, map);
}

// 북마크 삭제 처리
async function deletecheckBookmark(hospitalId) {
	if (confirm('이 북마크를 삭제하시겠습니까?')) {
		alert(`삭제 요청: 병원 ID ${hospitalId}`);
		console.log(hospitalId)
		await deleteBookmark(hospitalId);
		window.location.reload();
	}
}

async function deleteBookmark(hospitalId) {
	const response = await fetch("/service/users?command=delete-bookmark", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"hospitalId": hospitalId
		})
	});
	const json = await response.json();
	return json.isValid;
}