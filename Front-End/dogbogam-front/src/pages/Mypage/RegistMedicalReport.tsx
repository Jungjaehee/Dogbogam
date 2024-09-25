import { useState } from "react";
import BackButton from "../../assets/MyPage/BackButton.png";
import addPhotoIcon from "../../assets/MyPage/addPhotoIcon.png"; // + 모양 이모티콘 경로
import { useNavigate } from "react-router-dom";

interface MedicalRecord {
  record: {
    medicalRecordId: number;
    dogId: number;
    date: string;
    content: string | null;
    hospital: string;
    imageName?: string | null;
    imageUrl?: string | null;
    createdAt: Date;
    modifiedAt: Date;
  };
}


const RegistMedicalReport = () => {
  const navigate = useNavigate();

  // 진료기록 등록 요청 함수
  const ClickSubmitButton = () => {
    // api 연결
  }

  const ClickBackButton = () => {
    navigate(-1);
  };

  // 진료 구분 상태 관리
  const [selectedCategory, setSelectedCategory] = useState<string>("병원 진료");

  const handleCategoryClick = (category: string) => {
    setSelectedCategory(category);
  };

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
      {/* 뒤로가기 버튼 */}
      <button>
        <img
          src={BackButton}
          alt="Back Button"
          className="w-7 h-7 mb-2.5"
          onClick={ClickBackButton}
        />
      </button>

      {/* 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">
        진료 기록 추가
      </h1>
      <p className="text-gray-500 text-sm mb-2.5">
        반려견의 진료 기록을 확인하고 등록하세요
      </p>

      {/* 병원 입력 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          병원<span className="text-main-color ml-0.5">*</span>
        </label>
        <input
          type="text"
          className="w-full py-2 px-3 border border-gray-100 rounded-md text-gray-700 placeholder:text-sm"
          placeholder="병원을 입력해주세요"
          required
        />
      </div>

      {/* 진료 구분 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          진료 구분<span className="text-main-color ml-0.5">*</span>
        </label>
        <div className="flex space-x-4">
          <button
            className={`flex-1 py-2 px-4 rounded-md border text-sm font-medium ${
              selectedCategory === "병원 진료"
                ? "bg-select-category text-main-color border-main-color"
                : "bg-gray-100 text-gray-500 border-gray-100"
            }`}
            onClick={() => handleCategoryClick("병원 진료")}
          >
            병원 진료
          </button>
          <button
            className={`flex-1 py-2 px-4 rounded-md border text-sm font-medium ${
              selectedCategory === "예방 접종"
                ? "bg-select-category text-main-color border-main-color"
                : "bg-gray-100 text-gray-500 border-gray-100"
            }`}
            onClick={() => handleCategoryClick("예방 접종")}
          >
            예방 접종
          </button>
        </div>
      </div>

      <div className="flex flex-row space-x-4">
        {/* 날짜 선택 */}
        <div className="flex-1 mb-4">
          <label className="block text-gray-700 text-sm font-semibold mb-1">
            날짜<span className="text-main-color ml-0.5">*</span>
          </label>
          <input
            type="date"
            className="w-full py-2 px-3 border rounded-md text-gray-700"
            required
          />
        </div>

        {/* 시간 선택 */}
        <div className="flex-1 mb-4">
          <label className="block text-gray-700 text-sm font-semibold mb-1">
            시간<span className="text-main-color ml-0.5">*</span>
          </label>
          <input
            type="time"
            className="w-full py-2 px-3 border rounded-md text-gray-700"
            required
          />
        </div>
      </div>

      {/* 검사 결과 및 메모 추가 */}
      <div className="mb-4">
        <label className="block text-gray-500 text-xs mb-1">
          검사 결과지 및 메모 추가(선택)
        </label>

        <button className="w-7 h-7 flex items-center justify-center my-2">
          <img src={addPhotoIcon} alt="Add Icon" className="w-7 h-7" />
        </button>
        <textarea
          className="flex-1 w-full py-2 px-3 border rounded-md text-gray-700 placeholder:text-sm"
          placeholder="예) 저녁 7시 까지는 금식 입니다"
        />
      </div>

      {/* 등록 버튼 */}
      <div className="mt-4">
        <button
          className="w-full bg-yellow-400 text-white font-semibold py-3 rounded-lg shadow-md"
          onClick={ClickSubmitButton}
        >
          등록 하기
        </button>
      </div>
    </div>
  );
};

export default RegistMedicalReport;
