import { useState } from "react";
import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/BackButton.png";
import AIDiagnosisList from "./components/AIDiagnosisList";
import DiagnosisFilter from "./components/DiagnosisFilter"; 

const AIDiagnosis = () => {
  // 더미 데이터
  const dummyData = [
    {
      reportId: 1,
      dogId: 101,
      createdAt: "2023-09-10T10:00:00Z", 
      imageName: "eye_test.jpg",
      imageUrl: null, 
      normal: true, 
      diagnosisItem: "눈 건강 검사", 
    },
    {
      reportId: 2,
      dogId: 102,
      createdAt: "2024-06-30T14:00:00Z",
      imageName: "skin_test.jpg",
      imageUrl: null, 
      normal: false,
      diagnosisItem: "피부병 진단",
    },
    {
      reportId: 3,
      dogId: 103,
      createdAt: "2024-09-17T09:00:00Z",
      imageName: "joint_test.jpg",
      imageUrl: null,
      normal: false,
      diagnosisItem: "관절 검사",
    },
  ];

  const [filteredDiag, setFilteredDiag] = useState(dummyData); 
  const navigate = useNavigate();

  const ClickBackButton = () => {
    navigate(-1);
  }
  // 필터 변경 함수
  const FilterChange = (filter: string) => {
    if (filter === "") {
      setFilteredDiag(dummyData); 
    } else {
      
      const newFilteredDiag = dummyData.filter((item) =>
        item.diagnosisItem.includes(filter)
      );
      setFilteredDiag(newFilteredDiag);
    }
  };

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
      {/* 뒤로가기 버튼 */}
      <button>
        <img 
        src={BackButton} alt="Back Button" className="w-7 h-7 mb-2.5" 
        onClick={ClickBackButton}
        />
      </button>

      {/* 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">AI 진단 기록</h1>
      <p className="text-gray-500 text-sm mb-2.5">
        최대 10건의 상세 결과를 확인할 수 있어요.
      </p>

      {/* 필터 */}
      <DiagnosisFilter onFilterChange={FilterChange} />

      {/* AI 진단 결과 리스트 */}
      <AIDiagnosisList diagnosisArray={filteredDiag} />
    </div>
  );
};

export default AIDiagnosis;
