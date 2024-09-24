import React, { useEffect, useState } from "react";
import selectModal from "../../assets/images/selectModal.png";
// import axios from "axios";
// import { BASE_URL } from "../../api/APIconfig";
import Setting from "../../assets/icons/setting.png";
import Plus from "../../assets/icons/plus.png";
import ModalTop from "../../assets/icons/modalTop.png"

// 강아지 데이터의 타입 정의
interface Dog {
  dogId: number;
  dogName: string;
  imageUrl: string;
}

interface DogSelectModalProps {
  onClose: () => void;    // 모달 닫기 함수
  onConfirm: () => void;  // 확인 버튼 클릭 시 함수
}

const DogSelectModal: React.FC<DogSelectModalProps> = ({ onClose, onConfirm }) => {
  // 강아지 데이터 배열로 정의
  const [dogs, setDogs] = useState<Dog[]>([
    {
      dogId: 1,
      dogName: "새우",
      imageUrl: "https://img.freepik.com/free-photo/adorable-portrait-of-pomeranian-dog_23-2151771743.jpg"
    },
    {
      dogId: 2,
      dogName: "코코",
      imageUrl: "https://img.freepik.com/free-photo/cute-pomeranian-dog_23-2151771750.jpg"
    },
    // 추가 강아지 데이터
  ]);

  // 모달 바깥을 클릭했을 때 모달을 닫는 함수
  const handleModalClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (e.currentTarget === e.target) {
      onClose();
    }
  };

  // 주석 처리된 API 호출 부분을 사용할 경우 주석 해제
  // useEffect(() => {
  //   const fetchDogs = async () => {
  //     try {
  //       const response = await axios.get(`${BASE_URL}/members/pet`)
  //       const { memberPets } = response.data.data;
  //       setDogs(memberPets);
  //     } catch (error) {
  //       console.error("강아지 목록을 불러오는 데 실패했습니다.", error);
  //     }
  //   };
  //   fetchDogs();
  // }, []);

  return (
    <div className="fixed inset-0 flex items-end justify-center bg-black bg-opacity-50" onClick={handleModalClick}>
      <div className="bg-white w-[360px] h-[50vh] p-4 rounded-t-3xl shadow-lg overflow-y-auto">
        <div className="flex items-center grid place-items-center mb-10">
          <img src={ModalTop} className="max-w-11 max-h-5" />
        </div>
        <div className="flex items-center mb-6 cursor-pointer">
          <p className="text-lg font-bold mr-32"> 반려동물을 선택해주세요!</p>
          <img src={Setting} className="max-w-3 max-h-3 " />
        </div>
        <div className="mt-2 space-y-2 flex-grow overflow-y-auto mb-10">
            {dogs.length > 0 ? (
              dogs.map((dog) => (
                <div key={dog.dogId} className="flex items-center space-x-2 p-2 mb-6 bg-gray-100 rounded-lg cursor-pointer" onClick={() => onConfirm()}>
                  <img
                    src={dog.imageUrl}
                    alt={dog.dogName}
                    className="w-12 h-12 rounded-full object-cover mr-2"
                  />
                  <p className="text-md font-medium">{dog.dogName}</p>
                </div>
              ))
            ) : (
              <p>강아지 목록이 없습니다.</p>
            )}  
          
        </div>
        <div className="flex items-center justify-center mt-6 cursor-pointer" onClick={() => onConfirm()}>
          <img src={Plus} className="w-5 h-5 mr-2" />
          <p className="text-gray-400 font-medium text-sm">새로운 아이 등록하기</p>
        </div>
      </div>
    </div>
  );
};

export default DogSelectModal;
