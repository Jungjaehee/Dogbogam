import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { DogCard } from "../Home/components/dogCard";
import DogInfo from "./components/Doginfo";
import AIDiagnosisButton from "./components/Buttons/AIDiagnosisButton";
import MyInsuranceButton from "./components/Buttons/MyInsuranceButton";
import selectDogButton from "../../assets/MyPage/selectDogButton.png";
import SignUpDay from "./components/SignUpDay";
import MedicalRecordButton from "./components/Buttons/MedicalRecordButton";
import DogSelectModal from "../Home/components/dogSelectModal";
import useUserStore from "../../store/UseUserStore";

const MyPage = () => {
  const navigate = useNavigate();

  const { dogInfo } = useUserStore();
  const [openModal, setOpenModal] = useState(false);

  const ClickLogout = () => {
    window.localStorage.clear();
    navigate("/");
  };

  return (
    <div className="h-full flex flex-col bg-gray-0 pt-6 px-4">
      {/* 강아지 선택 메뉴 */}
      <div className="flex items-center">
        <DogCard dog={dogInfo}/>
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
        />
      )}

      <div>
        <DogInfo/>
        <SignUpDay createdTime={dogInfo.createdAt} />
        <div className="flex space-x-8">
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
