import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/MyPage/BackButton.png";
import AIDiagnosisList from "./components/AIDiagnosisList";
import DiagnosisFilter from "./components/DiagnosisFilter";
import { getDiagnosisRecords } from "../../api/aiDiagnosisAPI";
import useUserStore from "../../store/UseUserStore";
import useAIDiagnosisStore from "../../store/UseAIDiagnosisStore";

// 진단 항목의 타입을 정의합니다.
interface DiagnosisItem {
  diagnosisItem: string; // diagnosisItem 필드의 타입
  // 다른 필요한 필드들도 정의할 수 있습니다.
}

const AIDiagnosis = () => {
  const [filteredDiag, setFilteredDiag] = useState<DiagnosisItem[]>([]); // DiagnosisItem 배열로 타입을 지정

  const navigate = useNavigate();
  const { dogInfo } = useUserStore();
  const { setDiagnosisList } = useAIDiagnosisStore();

  const getDiagnosis = async () => {
    const listResponse = await getDiagnosisRecords(dogInfo.dogId);
    setDiagnosisList(listResponse);
    setFilteredDiag(listResponse); // 가져온 데이터를 필터에도 반영
  };

  useEffect(() => {
    const fetchDiagnosis = async () => {
      await getDiagnosis();
    };

    fetchDiagnosis();
  }, []); // 최초 1회 렌더링 될 때 데이터 호출

  const ClickBackButton = () => {
    navigate(-1);
  };

  // 필터 변경 함수
  const FilterChange = (filter: string) => {
    if (filter === "") {
      setFilteredDiag(filteredDiag); // 전체 데이터 보여주기
    } else {
      const newFilteredDiag = filteredDiag.filter(
        (item) => item.diagnosisItem.includes(filter) // diagnosisItem 필드를 사용
      );
      setFilteredDiag(newFilteredDiag);
    }
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
      <h1 className="text-xl text-gray-700 font-semibold mb-2">AI 진단 기록</h1>
      <p className="text-gray-500 text-sm mb-2.5">
        최대 10건의 상세 결과를 확인할 수 있어요.
      </p>

      {/* 필터 */}
      <DiagnosisFilter onFilterChange={FilterChange} />

      {/* AI 진단 결과 리스트 */}
      <AIDiagnosisList />
    </div>
  );
};

export default AIDiagnosis;
