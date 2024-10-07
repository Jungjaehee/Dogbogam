import Kakao from "../../components/kakao";
import { TopBar } from "../../components/Topbar";
import useUserStore from "../../store/UseUserStore";
import { useState, useEffect } from "react";
import { getHospitalList, AnimalHospital } from "../../api/hospitalAPI";


export const Map = () => {
  const { dogInfo } = useUserStore();
  const [hospitalList, setHospitalList] = useState<AnimalHospital[]>([]);
  const [selectedHospital, setSelectedHospital] = useState<AnimalHospital | null>(null);
  
  // 사용자 위치를 기반으로 병원 리스트 가져오기
  const fetchHospitalList = async (latitude: string, longitude: string) => {
    try {
      const data = await getHospitalList(latitude, longitude);
      setHospitalList(data);
    } catch (error) {
      console.error("병원 리스트 가져오기 실패", error);
    }
  };

  // 병원 선택 시 지도에서 해당 위치로 이동
  const handleHospitalSelect = (hospital: AnimalHospital) => {
    setSelectedHospital(hospital);
    // 여기에서 Kakao 지도에 병원 위치를 표시하도록 함
  };

  useEffect(() => {
    fetchHospitalList("35.200403208034665", "126.89636979402314");
  }, []);

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <TopBar pre={"/home"} title={""} skip={""} />
        <div className="mb-6">
          <p className="text-gray-800 text-xl font-bold">
            <span className="text-main-color">{`${dogInfo.name}`} </span>
            근처 <br /> 동물병원이에요
          </p>
        </div>
        <Kakao hospitals={hospitalList} selectedHospital={selectedHospital} />

        {/* 병원 리스트를 슬라이드 가능한 형태로 출력 */}
        <div className="flex overflow-x-auto gap-4 mt-4">
          {hospitalList.map((hospital) => (
            <div 
              key={hospital.animalHospitalId} 
              onClick={() => handleHospitalSelect(hospital)} 
              className={`flex-shrink-0 w-64 p-4 border rounded-lg shadow-lg cursor-pointer ${selectedHospital?.animalHospitalId === hospital.animalHospitalId ? 'bg-main-color text-white' : 'bg-white'}`}
            >
              <p className="font-bold">{hospital.name}</p>
              <p className="text-sm">{hospital.address}</p>
              <p className="text-sm">{hospital.phoneNumber}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};