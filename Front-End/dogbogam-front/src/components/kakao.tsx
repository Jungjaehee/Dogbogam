import React, { useEffect } from "react";

// 병원 데이터 타입 정의
interface AnimalHospital {
  latitude: string;
  longitude: string;
  name: string;
}

interface KakaoMapProps {
  hospitals: AnimalHospital[]; // 병원 배열
  selectedHospital: AnimalHospital | null; // 선택된 병원
}

const KakaoMap: React.FC<KakaoMapProps> = ({ hospitals, selectedHospital }) => {
  useEffect(() => {
    const initializeMap = () => {
      const container = document.getElementById("map") as HTMLElement; // 지도를 표시할 div 요소
      const options = {
        center: new window.kakao.maps.LatLng(36.3537, 127.341561), // 초기 지도 위치 (대전 좌표)
        level: 6, // 지도 확대 레벨
      };
      const map = new window.kakao.maps.Map(container, options); // 지도 생성
  
      const ps = new window.kakao.maps.services.Places(); // 장소 검색 객체 생성
  
      // '동물병원' 키워드로 장소를 검색할 때 지도 중심을 기준으로 검색
      const searchOptions = {
        location: new window.kakao.maps.LatLng(36.3509246, 127.3010886), // 지도 중심 위치를 검색 기준으로 설정
        radius: 5000, // 5km 반경 내에서 검색
      };
  
      ps.keywordSearch("동물병원", (data: kakao.maps.services.PlacesSearchResult[], status: kakao.maps.services.Status) => {
        if (status === window.kakao.maps.services.Status.OK) {
          // 검색된 장소들에 대한 마커 표시
          data.forEach((place) => {
            const markerPosition = new window.kakao.maps.LatLng(
              Number(place.y), // 위도
              Number(place.x) // 경도
            );
  
            const marker = new window.kakao.maps.Marker({
              position: markerPosition,
              map: map, // 마커를 지도에 추가
            });
  
            // 마커 클릭 시 병원 이름 표시
            const infowindow = new window.kakao.maps.InfoWindow({
              content: `<div style="padding:5px;font-size:12px;">${place.place_name}</div>`,
            });
  
            window.kakao.maps.event.addListener(marker, "click", () => {
              infowindow.open(map, marker);
            });
          });
        } else {
          console.error("검색 결과가 없습니다.");
        }
      }, searchOptions); // 검색 옵션을 추가하여 지도 중심을 기준으로 검색
    };
  
    // 카카오맵 스크립트가 로드되었는지 확인
    if (window.kakao && window.kakao.maps) {
      initializeMap(); // 이미 로드된 경우 지도 생성
    } else {
      // 카카오맵 스크립트를 동적으로 로드
      const script = document.createElement("script");
      script.src = `https://dapi.kakao.com/v2/maps/sdk.js?appkey=f33690c7b7744f9985afb20ba7c3598c&autoload=false&libraries=services`;
      document.head.appendChild(script);
  
      // 스크립트가 로드된 후 지도 생성
      script.onload = () => {
        window.kakao.maps.load(() => {
          initializeMap(); // 지도를 초기화하는 함수 호출
        });
      };
    }
  }, [hospitals, selectedHospital]);
  

  return (
    <div
      id="map"
      style={{
        width: "100%",
        height: "500px", // 지도의 크기 설정
      }}
    ></div>
  );
};

export default KakaoMap;
