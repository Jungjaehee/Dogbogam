import { useState, useEffect } from "react";
import { TopBar } from "../../components/Topbar";
import AIDiagnosisList from "./components/AIDiagnosisList";
import DiagnosisFilter from "./components/DiagnosisFilter";
import { getDiagnosisRecords } from "../../api/aiDiagnosisAPI";
import useUserStore from "../../store/UseUserStore";
import useAIDiagnosisStore from "../../store/UseAIDiagnosisStore";
import { AiDiagnosis } from "../../models/record.model"

const AIDiagnosis = () => {
  const [filteredDiag, setFilteredDiag] = useState<AiDiagnosis[]>([]); // 필터된 진단 리스트
  const { dogInfo } = useUserStore();
  const { setDiagnosisList, diagnosisList } = useAIDiagnosisStore(); // 원본 데이터를 상태로 관리

  const getDiagnosis = async () => {
    const listResponse = await getDiagnosisRecords(dogInfo.dogId);
    setDiagnosisList(listResponse.diagnoses); // 원본 데이터를 저장
    setFilteredDiag(listResponse.diagnoses); // 처음엔 전체 데이터를 필터에 반영
  };

  useEffect(() => {
    const fetchDiagnosis = async () => {
      await getDiagnosis();
    };

    fetchDiagnosis();
  }, []); // 최초 1회 렌더링 될 때 데이터 호출

  // 필터 변경 함수
  const FilterChange = (filter: string) => {
    if (filter === "") {
      // 필터가 비어 있을 경우 전체 데이터를 표시 (원본 데이터를 사용)
      setFilteredDiag(diagnosisList);
    } else {
      // 필터가 있을 경우 진단 항목을 필터링
      const newFilteredDiag = diagnosisList.filter(
        (item) => item.diagnosisItem.includes(filter) // diagnosisItem 필드를 기준으로 필터링
      );
      setFilteredDiag(newFilteredDiag);
    }
  };

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0 overflow-y-scroll">
      <TopBar pre={""} title={""} skip={""} />
      {/* 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">AI 진단 기록</h1>
      <p className="text-gray-500 text-sm mb-2.5">
        최대 10건의 상세 결과를 확인할 수 있어요.
      </p>
      {/* 필터 */}
      <DiagnosisFilter onFilterChange={FilterChange} />
      {/* AI 진단 결과 리스트 */}
      <AIDiagnosisList diagnosisList={filteredDiag} />{" "}
      {/* 필터링된 데이터를 넘김 */}
    </div>
  );
};

export default AIDiagnosis;
