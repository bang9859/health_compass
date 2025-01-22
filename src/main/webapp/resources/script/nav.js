window.addEventListener("load", function() {
    let currentImageIndex = 0;
    const images = [
        "https://www.seochonoin.org/data/editor/2110/thumb-1847822453_1635121464.061_1400x1980.jpg",
        "https://www.dailydental.co.kr/data/photos/20200936/art_1599189905_a38ed7.bmp",
        "https://www.kdca.go.kr/html/a2/namoimage/images/000055/%EA%B1%B4%EA%B0%95%ED%95%9C_%EA%B2%A8%EC%9A%B8%EB%82%98%EA%B8%B0_%ED%8F%AC%EC%8A%A4%ED%84%B0.jpg"
    ];

    const imgBox = document.getElementById('img-box');
    if (!imgBox) {
        console.error('No image container found.');
        return;
    }

    // 이미지 요소들을 동적으로 생성하여 img-box에 추가
    images.forEach((src, index) => {
        const img = document.createElement('img');
        img.src = src;
        img.style.width = '100%';  // 이미지 크기 조정
        img.style.height = 'auto'; // 높이는 자동으로 조정
        img.style.display = index === currentImageIndex ? 'none' : 'none';  // 초기에는 첫번째 이미지만 보이게
        imgBox.appendChild(img);
    });

    const imgElements = imgBox.getElementsByTagName('img');
    if (imgElements.length === 0) {
        console.error('No image elements found.');
        return;
    }

    function changeImage() {
        imgElements[currentImageIndex].style.display = 'none';  // 현재 이미지 숨기기

        // 이미지 인덱스 변경 (순환)
        currentImageIndex = (currentImageIndex + 1) % images.length;

        imgElements[currentImageIndex].style.display = 'block';  // 새로운 이미지 보이게 하기
    }

    // 5초마다 이미지 변경
    setInterval(changeImage, 5000);
});

