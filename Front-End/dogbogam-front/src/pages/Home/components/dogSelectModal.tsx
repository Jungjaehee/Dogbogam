import React, { useState } from "react";
import { DogInfo } from "../../../store/UseDogStore";
import Setting from "../../../assets/icons/setting.png";
import Plus from "../../../assets/icons/plus.png";
import ModalTop from "../../../assets/icons/modalTop.png";


interface DogSelectModalProps {
  onClose: () => void; 
  onConfirm: (dog: Partial<DogInfo>) => void;
}

const DogSelectModal: React.FC<DogSelectModalProps> = ({
  onClose,
  onConfirm,
}) => {
  //api 연결 후에 store에서 불러오기
  const [dogs] = useState<DogInfo[]>([
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
  className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-20"
  onClick={handleModalClick}
>
  <div className="bg-white w-[360px] h-[53vh] p-5 rounded-t-3xl shadow-lg transform translate-y-40">
        <div className="flex items-center justify-center place-items-center mb-10">
          <img src={ModalTop} className="max-w-11 max-h-5" />
        </div>
        <div className="flex items-center mb-10 cursor-pointer mt-10 space-x-28">
          <p className="text-lg font-bold">반려동물을 선택해주세요!</p>
          <img src={Setting} className="w-3 h-3" />
        </div>
        <div className="mt-2 space-y-2 flex-grow overflow-y-auto">
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
          className="flex items-center justify-center cursor-pointer mt-10"
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
