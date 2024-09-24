import { useNavigate } from "react-router-dom";
import { calRelativeTime } from "../../../utils/calcDate";
import { calcAIstatus, getComment } from "../../../utils/calcStatus";

interface AIDiagnosis {
  diagnosis: {
    reportId: number;
    dogId: number;
    createdAt: string;
    imageName?: string | null;
    imageUrl?: string | null;
    normal: boolean;
    diagnosisItem: string;
  };
}

const AIDiagnosisItem = ({ diagnosis }: AIDiagnosis) => {
  const navigate = useNavigate();

  const ClickDiagnosis = () => {
    navigate(`${diagnosis.reportId}`);
  };

  const recordDate = new Date(diagnosis.createdAt);
  const relativeTime = calRelativeTime(recordDate);

  // 상태 계산 함수
  const status = calcAIstatus(diagnosis.normal);

  // 코멘트 함수
  const comment = getComment(status);

  // status에 따라 글자 색 바꿈
  const statusClass = status === "좋음" ? "text-good-text" : "text-bad-text";

  return (
    <div
      className="p-4 bg-white rounded-lg shadow-md mb-4 flex items-center justify-between"
      onClick={ClickDiagnosis}
    >
      {/* 진단 상태 */}
      <div className="">
        <span className={`font-semibold ${statusClass}`}>{status}</span>
      </div>

      {/* 진단 항목 */}
      <div className="flex-2 ml-6">
        <span className="text-gray-700 font-semibold">
          {diagnosis.diagnosisItem}
        </span>
        <p className="text-gray-500 text-xs">{comment}</p>
      </div>

      {/* 진단 생성일 */}
      <div className="flex-1 text-right">
        <span className="text-gray-500 text-xs">{relativeTime}</span>
      </div>
    </div>
  );
};

export default AIDiagnosisItem;
