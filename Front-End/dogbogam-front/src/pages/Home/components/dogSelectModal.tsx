import React, { useState } from "react";
import Setting from "../../../assets/icons/setting.png";
import Plus from "../../../assets/icons/plus.png";
import ModalTop from "../../../assets/icons/modalTop.png";

// 강아지 데이터의 타입 정의
interface Dog {
  dogId: number;
  dogName: string;
  imageUrl: string;
}

interface DogSelectModalProps {
  onClose: () => void;    // 모달 닫기 함수
  onConfirm: (dog: Dog) => void;  // 확인 버튼 클릭 시 선택된 강아지를 전달하는 함수
}

const DogSelectModal: React.FC<DogSelectModalProps> = ({ onClose, onConfirm }) => {
  const [dogs] = useState<Dog[]>([
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
  ]);

  // 모달 바깥을 클릭했을 때 모달을 닫는 함수
  const handleModalClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (e.currentTarget === e.target) {
      onClose();
    }
  };

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
              <div key={dog.dogId} className="flex items-center space-x-2 p-2 mb-6 bg-gray-100 rounded-lg cursor-pointer hover:bg-main-color" onClick={() => onConfirm(dog)}>
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
        <div className="flex items-center justify-center mt-6 cursor-pointer"
        onClick={() => console.log('새로운 아이 등록')}>
          <img src={Plus} className="w-5 h-5 mr-2" />
          <p className="text-gray-400 font-medium text-sm">새로운 아이 등록하기</p>
        </div>
      </div>
    </div>
  );
};

export default DogSelectModal;
