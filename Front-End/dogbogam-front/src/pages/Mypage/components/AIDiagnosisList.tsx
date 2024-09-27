import AIDiagnosisItem from "./AIDiagnosisItem";
import NonMedicalRecordIcon from "../../../assets/MyPage/NonMedicalRecordIcon.png";
import { AiDiagnosis } from "../../../models/record.model";

interface DiagnosisList {
  diagnosisArray: AiDiagnosis[]; // AiDiagnosis 인터페이스로 타입 지정
}

const AIDiagnosisList = ({ diagnosisArray }: DiagnosisList) => {
  return (
    <div>
      {diagnosisArray.length > 0 ? (
        // 진단 결과가 있을 때
        diagnosisArray.map((diagnosis) => (
          <AIDiagnosisItem
            key={diagnosis.reportId.toString()}
            diagnosis={diagnosis}
          />
        ))
      ) : (
        // 진단 기록이 없을 때
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
