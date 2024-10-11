import React, { useEffect } from "react";
import Plus from "../../../assets/icons/plus.png";
import ModalTop from "../../../assets/icons/modalTop.png";
import useUserStore from "../../../store/UseUserStore";
import { getDogInfo, getDogList } from "../../../api/dogAPI";
import { useNavigate } from "react-router-dom";

interface DogSelectModalProps {
  onClose: () => void;
}

const DogSelectModal: React.FC<DogSelectModalProps> = ({ onClose }) => {
  //api 연결 후에 store에서 불러오기
  const { token, dogList, setDogInfo , setDogList } = useUserStore();
  const navigate = useNavigate();

  const onConfirm = async (dogId: number) => {
    const dogResponse = await getDogInfo(token, dogId);
    console.log("강아지 상세조회: ", dogResponse);
    setDogInfo(dogResponse);
    onClose();
  };
  const fetchDogData = async () => {
    const responseData = await getDogList(token);
    setDogList(responseData);
  }

  useEffect(() => {
    fetchDogData()
  },[])

  const ClickRegistButton = () => {
    navigate("/regist")
  }
  

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
      <div className="bg-white h-[53vh] w-full max-w-[440px] p-5 rounded-t-3xl shadow-lg absolute bottom-0">
        <div className="flex items-center justify-center place-items-center mb-10">
          <img src={ModalTop} className="max-w-11 max-h-5" />
        </div>
        <div className="flex items-center mb-10 cursor-pointer mt-10 space-x-28">
          <p className="text-lg font-bold">반려동물을 선택해주세요!</p>
        </div>
        <div className="mt-2 space-y-2 flex-grow overflow-y-auto">
          {dogList.length > 0 ? (
            dogList.map((dog) => (
              <div
                key={dog.dogId}
                className="flex items-center space-x-2 p-2 bg-gray-100 rounded-lg cursor-pointer hover:bg-main-color"
                onClick={() => onConfirm(dog.dogId)}
              >
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
        <div className="flex items-center justify-center cursor-pointer mt-10">
          <img src={Plus} className="w-5 h-5 mr-2" />
          <p
            className="text-gray-400 font-medium text-sm"
            onClick={() => ClickRegistButton()}
          >
            새로운 아이 등록하기
          </p>
        </div>
      </div>
    </div>
  );
};

export default DogSelectModal;
