import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { DogCard } from "../../components/Card/dogCard"
import DogSelectModal from "../../components/Modal/dogSelectModal";
import FindDog from "../../assets/images/findDog.png";
import Insurance from "../../assets/images/insurance.png";
import Supplement from "../../assets/images/supplement.png";
import Hospital from "../../assets/images/hospital.png";
import Left from "../../assets/icons/left.png";
import Right from "../../assets/icons/right.png";
import Down from "../../assets/icons/down.png";

// dog 인터페이스 정의
interface Dog {
  // 강아지 이름
  name: string;
}

export const Home = () => {
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleModalClose = () => {
    setIsModalOpen(false);
  }
  const handleConfirm = () => {
    setIsModalOpen(false);
  }

  // 강아지 데이터 불러오기
  const dog: Dog = {
    name: "새우",
  };


  return (
    // <div className="h-full flex flex-col justify-start pt-6 px-4 bg-gray-0 text-left">
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        {/* 강아지 카드 */}
        <div className="w-32 h-14 flex items-center space-x-2 bg-white rounded-lg shadow-md">
          <DogCard name={dog.name} />
          <img
            src={Down}
            className="w-4 h-4 cursor-pointer"
            onClick={() => setIsModalOpen(true)} // 클릭 시 모달 열기
          />
        </div>

        {isModalOpen && (
          <DogSelectModal onClose={handleModalClose} onConfirm={handleConfirm} />
        )}

        

        <div className="mt-6">
          <p className="text-gray-800 text-2xl font-extrabold">
            오늘의 <span className="text-main-color">{`${dog.name}`}</span>는, 
            <br />
            산책했나요?
          </p>
        </div>

        <div className="mt-8 p-4 w-50 h-35 bg-gray-200 rounded-lg shadow-md flex items-center justify-between" onClick={() => {navigate("/home/breed")}}>
          <p className="text-gray-700 text-lg font-semibold">
            재미로보는 <br /> 우리아이 견종찾기
          </p>
          <img src={FindDog} className="w-25 h-20" />
        </div>

        <div className="mt-10 w-full h-auto flex space-x-4">
          <div className="p-4 w-1/2 h-40 bg-main-color rounded-lg shadow-md flex flex-col justify-between" onClick={() => {navigate("/home/insurance")}}>
            <p className="self-start text-gray-0 text-lg font-semibold">보험 추천</p>
            <img src={Insurance} alt="보험" className="self-end w-20 h-20" />
          </div>

          <div className="p-4 w-1/2 h-40 bg-blue-200 rounded-lg shadow-md flex flex-col justify-between" onClick={() => {navigate("/home/supplement")}}>
            <p className="self-start text-gray-0 text-lg font-semibold">영양제 추천</p>
            <img src={Supplement} alt="영양제" className="self-end w-20 h-20" />
          </div>
        </div>

        <div className="mt-12 p-4 w-50 h-35 bg-gray-200 rounded-lg shadow-md flex items-center justify-between">
          <img src={Left} className="w-3 h-4" />
          <img src={Hospital} className="w-25 h-20" />
          <p className="text-gray-700 text-lg font-semibold">
            집주변 동물병원을 <br /> 손 쉽게 찾아봐요!
          </p>
          <img src={Right} className="w-3 h-4" />
        </div>
      </div>
      
    </div>
  );
};
