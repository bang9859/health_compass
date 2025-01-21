document.addEventListener("DOMContentLoaded", function () {
    console.log("JavaScript 실행됨");

    if (!hospitalList || hospitalList.length === 0) {
        console.warn("hospitalList가 비어 있습니다!");
        return;
    }

	console.log("Hospital List:", hospitalList);
    // 사용자 위치 가져오기
    navigator.geolocation.getCurrentPosition(
        function (position) {
            const userLat = position.coords.latitude; // 사용자의 위도
            const userLon = position.coords.longitude; // 사용자의 경도

            console.log("User Location:", userLat, userLon);

            // 지도 초기화
            const mapContainer = document.getElementById("map");
            const mapOption = {
                center: new kakao.maps.LatLng(userLat, userLon), // 사용자의 현재 위치를 지도 중심으로 설정
                level: 3, // 확대 레벨
            };
            const map = new kakao.maps.Map(mapContainer, mapOption);

            // 사용자 위치에 마커 추가
            const userMarker = new kakao.maps.Marker({
                map,
                position: new kakao.maps.LatLng(userLat, userLon),
                title: "현재 위치",
            });

            const userInfowindow = new kakao.maps.InfoWindow({
                content: `<div style="padding:5px;">현재 위치</div>`,
            });
            userInfowindow.open(map, userMarker);

            // 병원 데이터를 기반으로 마커 생성
            hospitalList.forEach((hospital) => {
                const markerPosition = new kakao.maps.LatLng(hospital.latitude, hospital.longitude);
                const marker = new kakao.maps.Marker({
                    map,
                    position: markerPosition,
                    title: hospital.name,
                });

                const infowindow = new kakao.maps.InfoWindow({
                    content: `<div style="padding:5px;">${hospital.name}</div>`,
                });

                kakao.maps.event.addListener(marker, "click", () => {
                    infowindow.open(map, marker);
                });
            });

            console.log("병원 데이터와 지도 로드 완료!");
        },
        function (error) {
            console.error("사용자 위치를 가져올 수 없습니다:", error);
        }
    );
});
