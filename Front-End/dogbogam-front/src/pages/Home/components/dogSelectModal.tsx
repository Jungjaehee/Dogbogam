import React, { useState } from "react";
import Setting from "../../../assets/icons/setting.png";
import Plus from "../../../assets/icons/plus.png";
import ModalTop from "../../../assets/icons/modalTop.png";

// 강아지 선택하면 마이페이지에서 다시 정보 렌더링 해야해서 인터페이스 타입 맞췄어요
interface Dog {
  dogId: number;
  memberId: number;
  isDeleted: boolean;
  name: string;
  breed: string;
  gender: string;
  birthDate?: Date | null;
  weight?: number | null;
  isNeutered?: boolean | null;
  imageName?: string | null;
  imageUrl?: string | null;
  createdTime: Date;
  modifiedTime: Date | null;
}

interface DogSelectModalProps {
  onClose: () => void; 
  onConfirm: (dog: Dog) => void;
}

const DogSelectModal: React.FC<DogSelectModalProps> = ({
  onClose,
  onConfirm,
}) => {
  const [dogs] = useState<Dog[]>([
    {
      dogId: 1,
      memberId: 1,
      isDeleted: false,
      name: "새우",
      breed: "포메라니안",
      gender: "female",
      birthDate: new Date(2015, 5, 15),
      weight: 4.3,
      isNeutered: true,
      imageName: "saewoo.jpg",
      imageUrl:
        "https://img.freepik.com/free-photo/adorable-portrait-of-pomeranian-dog_23-2151771743.jpg",
      createdTime: new Date(2023, 0, 1),
      modifiedTime: null,
    },
    {
      dogId: 2,
      memberId: 2,
      isDeleted: false,
      name: "코코",
      breed: "말티즈",
      gender: "male",
      birthDate: new Date(2017, 8, 20),
      weight: 5.0,
      isNeutered: false,
      imageName: "koko.jpg",
      imageUrl:
        "https://img.freepik.com/free-photo/cute-pomeranian-dog_23-2151771750.jpg",
      createdTime: new Date(2022, 5, 1),
      modifiedTime: null,
    },
  ]);

  const handleModalClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (e.currentTarget === e.target) {
      onClose();
    }
  };

  return (
    <div
      className="fixed inset-0 flex items-end justify-center bg-black bg-opacity-50 z-20"
      onClick={handleModalClick}
    >
      <div className="bg-white w-[360px] h-[50vh] p-4 rounded-t-3xl shadow-lg overflow-y-auto">
        <div className="flex items-center place-items-center mb-10">
          <img src={ModalTop} className="max-w-11 max-h-5" />
        </div>
        <div className="flex items-center mb-6 cursor-pointer">
          <p className="text-lg font-bold mr-32"> 반려동물을 선택해주세요!</p>
          <img src={Setting} className="max-w-3 max-h-3 " />
        </div>
        <div className="mt-2 space-y-2 flex-grow overflow-y-auto mb-10">
          {dogs.length > 0 ? (
            dogs.map((dog) => (
              <div
                key={dog.dogId}
                className="flex items-center space-x-2 p-2 mb-6 bg-gray-100 rounded-lg cursor-pointer hover:bg-main-color"
                onClick={() => onConfirm(dog)}
              >
                <img
                  src={dog.imageUrl}
                  alt={dog.name}
                  className="w-12 h-12 rounded-full object-cover mr-2"
                />
                <p className="text-md font-medium">{dog.name}</p>
              </div>
            ))
          ) : (
            <p>강아지 목록이 없습니다.</p>
          )}
        </div>
        <div
          className="flex items-center justify-center mt-6 cursor-pointer"
          onClick={() => console.log("새로운 아이 등록")}
        >
          <img src={Plus} className="w-5 h-5 mr-2" />
          <p className="text-gray-400 font-medium text-sm">
            새로운 아이 등록하기
          </p>
        </div>
      </div>
    </div>
  );
};

export default DogSelectModal;
