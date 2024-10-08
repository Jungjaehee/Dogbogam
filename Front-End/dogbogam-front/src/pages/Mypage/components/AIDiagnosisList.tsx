import AIDiagnosisItem from "./AIDiagnosisItem";
import NonMedicalRecordIcon from "../../../assets/MyPage/NonMedicalRecordIcon.png";
import { AiDiagnosis } from "../../../models/record.model";

interface AiDiagnosisListProps {
  diagnosisList: AiDiagnosis[];
}

const AIDiagnosisList = ({ diagnosisList }: AiDiagnosisListProps) => {
  console.log(diagnosisList);

  return (
    <div>
      {diagnosisList.length > 0 ? (
        diagnosisList.map((diagnosis: AiDiagnosis) => (
          <AIDiagnosisItem
            key={diagnosis.aiDiagnosisId.toString()}
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
