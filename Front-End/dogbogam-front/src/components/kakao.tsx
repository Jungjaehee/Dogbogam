import React, { useEffect } from "react";

const KakaoMap: React.FC = () => {
  useEffect(() => {
    // 카카오맵 스크립트가 로드되었는지 확인
    if (window.kakao && window.kakao.maps) {
      createMap(); // 이미 로드된 경우 지도 생성
    } else {
      // 카카오맵 스크립트를 동적으로 로드
      const script = document.createElement("script");
      script.src = `https://dapi.kakao.com/v2/maps/sdk.js?appkey=f33690c7b7744f9985afb20ba7c3598c&autoload=false`;
      document.head.appendChild(script);

      // 스크립트가 로드된 후 지도 생성
      script.onload = () => {
        window.kakao.maps.load(() => {
          createMap(); // 지도를 초기화하는 함수 호출
        });
      };
    }
  }, []);

  // 지도를 생성하는 함수
  const createMap = () => {
    const container = document.getElementById("map"); // 지도를 표시할 div 요소
    const options = {
      center: new window.kakao.maps.LatLng(37.5665, 126.9780), // 지도 중심 좌표 (서울)
      level: 3, // 지도 확대 레벨
    };
    const map = new window.kakao.maps.Map(container, options); // 지도 생성

    // 마커 추가
    const markerPosition = new window.kakao.maps.LatLng(37.5665, 126.9780);
    const marker = new window.kakao.maps.Marker({
      position: markerPosition,
    });
    marker.setMap(map); // 마커를 지도에 추가
  };

  return (
    <div
      id="map"
      style={{
        width: "100%",
        height: "70%", // 지도의 크기 설정
      }}
    ></div>
  );
};

export default KakaoMap;
