import { useState } from "react";
import KakaoMap from "../../components/kakao";
import { TopBar } from "../../components/Topbar";
import useUserStore from "../../store/UseUserStore";

export interface AnimalHospital {
  latitude: string;
  longitude: string;
  name: string;
}

export const Map = () => {
  const { dogInfo } = useUserStore();
  
  // 임의로 선택한 병원 데이터를 저장하는 상태 (선택된 병원이 있으면 지도에서 이동)
  const [selectedHospital, setSelectedHospital] = useState<AnimalHospital | null>(null);

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        {/* TopBar 컴포넌트 */}
        <TopBar pre={"/home"} title={""} skip={""} />
        <div className="mb-6">
          <p className="text-gray-800 text-xl font-bold">
            <span className="text-main-color">{`${dogInfo.name}`} </span>
            근처 <br /> 동물병원이에요
          </p>
        </div>

        {/* KakaoMap 컴포넌트 */}
        {/* hospitals는 카카오맵에서 검색된 데이터를 가져오기 때문에 빈 배열로 설정 */}
        <KakaoMap hospitals={[]} selectedHospital={selectedHospital} />

        {/* 선택된 병원이 있을 경우 병원 리스트를 보여주고 선택한 병원을 업데이트하는 동작 */}
        {selectedHospital && (
          <div className="flex flex-col items-center">
            <h2 className="text-xl font-bold">선택된 병원:</h2>
            <p>{selectedHospital.name}</p>
            <button
              className="p-2 bg-main-color text-white rounded-md mt-4"
              onClick={() => setSelectedHospital(null)} // 병원 선택 취소
            >
              병원 선택 취소
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default Map;
