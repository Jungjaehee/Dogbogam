import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { TopBar } from "../../components/Topbar"
import MedicalRecordForm from "./components/MedicalRecordForm";
import VaccinationRecordForm from "./components/VaccinationRecordForm";

const RegistMedicalReport = () => {
  const navigate = useNavigate();
  const [currentType, setCurrentType] = useState("병원 진료");

  const handleBack = () => {
    navigate(-1);
  };

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
      <TopBar pre={""} title={""} skip={""} />
      <h1 className="text-xl text-gray-700 font-semibold mb-2">
        진료 기록 추가
      </h1>
      <p className="text-gray-500 text-sm mb-2.5">
        반려견의 진료 기록을 확인하고 등록하세요
      </p>

      {/* 진료 구분 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          진료 구분<span className="text-main-color ml-0.5">*</span>
        </label>
        <div className="flex space-x-4">
          <button
            className={`flex-1 py-2 px-4 rounded-md border text-sm font-medium ${
              currentType === "병원 진료"
                ? "bg-select-category text-main-color border-main-color"
                : "bg-gray-100 text-gray-500 border-gray-100"
            }`}
            onClick={() => setCurrentType("병원 진료")}
          >
            병원 진료
          </button>
          <button
            className={`flex-1 py-2 px-4 rounded-md border text-sm font-medium ${
              currentType === "예방 접종"
                ? "bg-select-category text-main-color border-main-color"
                : "bg-gray-100 text-gray-500 border-gray-100"
            }`}
            onClick={() => setCurrentType("예방 접종")}
          >
            예방 접종
          </button>
        </div>
      </div>

      {/* 진료 구분에 따른 컴포넌트 렌더링 */}
      {currentType === "병원 진료" ? (
        <MedicalRecordForm handleBack={handleBack} />
      ) : (
        <VaccinationRecordForm handleBack={handleBack} />
      )}
    </div>
  );
};

export default RegistMedicalReport;
