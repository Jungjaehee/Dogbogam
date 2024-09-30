import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { DogCard } from "../Home/components/dogCard";
import DogInfo from "./components/Doginfo";
import AIDiagnosisButton from "./components/Buttons/AIDiagnosisButton";
import MyInsuranceButton from "./components/Buttons/MyInsuranceButton";
import selectDogButton from "../../assets/MyPage/selectDogButton.png";
import SignUpDay from "./components/SignUpDay";
import imageUrl from "../../assets/MyPage/saewooIcon.png";
import MedicalRecordButton from "./components/Buttons/MedicalRecordButton";
import DogSelectModal from "../Home/components/dogSelectModal";

import { Dog } from "../../models/dog.model";

const MyPage = () => {
  const navigate = useNavigate();

  const dummyDogs: Dog[] = [
    {
      dogId: 1,
      memberId: 1,
      isDeleted: false,
      name: "새우",
      breed: "포메라니안",
      gender: "female",
      birthDate: new Date(2015, 5, 15), // 2015년 6월 15일생
      weight: 4.3,
      isNeutered: true,
      imageName: "saewoo.jpg",
      imageUrl: imageUrl,
      createdTime: new Date(2023, 0, 1), // 2023년 1월 1일 가입
      modifiedTime: null,
    },
    {
      dogId: 2,
      memberId: 2,
      isDeleted: false,
      name: "코코",
      breed: "포메라니안",
      gender: "male",
      birthDate: new Date(2017, 8, 20), // 2017년 9월 20일생
      weight: 5.0,
      isNeutered: false,
      imageName: "koko.jpg",
      imageUrl:
        "https://img.freepik.com/free-photo/cute-pomeranian-dog_23-2151771750.jpg",
      createdTime: new Date(2022, 5, 1), // 2022년 6월 1일 가입
      modifiedTime: null,
    },
  ];

  const [currentDog, setCurrentDog] = useState<Dog>(dummyDogs[0]); // 초기 선택된 강아지
  const [openModal, setOpenModal] = useState(false);

  const handleDogSelect = (dog: Dog) => {
    setCurrentDog(dog);
    setOpenModal(false);
  };

  const ClickLogout = () => {
    navigate("/");
  };

  return (
    <div className="h-full flex flex-col bg-gray-0 pt-6 px-4">
      {/* 강아지 선택 메뉴 */}
      <div className="flex items-center">
        <DogCard imageUrl={currentDog.imageUrl} dogName={currentDog.name} />
        <button
          className="text-sm text-gray-500"
          onClick={() => setOpenModal(true)}
        >
          <img
            src={selectDogButton}
            alt="selectDogButton"
            className="w-4 h-4 mb-2"
          />
        </button>
      </div>

      {openModal && (
        <DogSelectModal
          onClose={() => setOpenModal(false)}
          onConfirm={handleDogSelect}
        />
      )}

      <div>
        <DogInfo dog={currentDog} />
        <SignUpDay createdTime={currentDog.createdTime} />
        <div className="flex space-x-6">
          <AIDiagnosisButton />
          <MyInsuranceButton />
        </div>
        <MedicalRecordButton />
      </div>

      <div className="flex justify-center pb-4">
        <button
          className="text-gray-500 hover:text-red-500"
          onClick={ClickLogout}
        >
          로그아웃
        </button>
      </div>
    </div>
  );
};

export default MyPage;
