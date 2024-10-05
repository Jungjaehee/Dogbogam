import { useEffect, useState } from "react";
import AIDiagnosisItem from "./AIDiagnosisItem";
import NonMedicalRecordIcon from "../../../assets/MyPage/NonMedicalRecordIcon.png";
import useAIDiagnosisStore from "../../../store/UseAIDiagnosisStore"; // zustand 스토어에서 진단 데이터 가져오기
import { AiDiagnosis } from "../../../models/record.model";

const AIDiagnosisList = () => {
  const { diagnosisList } = useAIDiagnosisStore(); // zustand 스토어에서 전역 상태 가져오기
  const [diagnosisArray, setDiagnosisArray] = useState<AiDiagnosis[]>([]); // 로컬 상태 관리

  useEffect(() => {
    const data = localStorage.getItem("diagnosisStorage");
    if (data) {
      const parsedData = JSON.parse(data);
      const storedDiagnosisArray =
        parsedData.state.diagnosisList.data.diagnoses;
      setDiagnosisArray(storedDiagnosisArray);
    } else {
      setDiagnosisArray(diagnosisList); // 스토어에서 가져온 데이터를 로컬 상태에 반영
    }
  }, [diagnosisList]); // 진단 리스트 변경 시 다시 반영

  return (
    <div>
      {diagnosisArray.length > 0 ? (
        diagnosisArray.map((diagnosis: AiDiagnosis) => (
          <AIDiagnosisItem
            key={diagnosis.reportId.toString()}
            diagnosis={diagnosis}
          />
        ))
      ) : (
        <div className="flex flex-col items-center justify-center h-[calc(100vh-200px)]">
          <img
            src={NonMedicalRecordIcon}
            alt="NonMedicalRecord Icon"
            className="w-18 h-18 mb-4"
          />
          <span className="text-lg font-semibold text-gray-500">
            AI 진단 기록이 없어요.
          </span>
        </div>
      )}
    </div>
  );
};

export default AIDiagnosisList;
