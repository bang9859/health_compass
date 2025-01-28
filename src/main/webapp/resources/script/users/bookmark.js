//<a href="https://www.flaticon.com/kr/free-icons/" title="병원 아이콘">병원 아이콘 제작자: Freepik - Flaticon</a>
window.addEventListener("load", function() {
    // 지도 초기화
	initializeMap();
});

// 지도 초기화 함수
function initializeMap() {
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(37.4947129029365, 127.03009668045205), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    // 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
    return new kakao.maps.Map(mapContainer, mapOption);
}

// 주소로 좌표를 변환하여 지도를 이동하는 함수
function moveToAddress(address, name, map) {
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(address, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: `<div style="width:150px;text-align:center;padding:6px 0;">${name}</div>`
            });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
			map.setLevel(1, { animate: true });
        }
    });
}

// 북마크 클릭 처리
function handleBookmarkClick(address, name) {
	alert(`병원 주소: ${address}`);
	
	var map = initializeMap();
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