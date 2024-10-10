import { useNavigate } from "react-router-dom";
import { calRelativeTime } from "../../../utils/calcDate";
import { calcAIstatus, getComment , getStatusColor } from "../../../utils/calcStatus";
import type { AiDiagnosis } from "../../../models/record.model";

interface AIDiagnosisItemProps {
  diagnosis: AiDiagnosis;
}

const AIDiagnosisItem = ({ diagnosis }: AIDiagnosisItemProps) => {
  const navigate = useNavigate();
  const ClickDiagnosis = () => {
    navigate(`/mypage/aiDiagnosis-detail/${diagnosis.aiDiagnosisId}`, {
      state: { id: diagnosis.aiDiagnosisId },
    });
  };
  const relativeTime = calRelativeTime(new Date(diagnosis.createdAt));

  let status = "";

  if (!diagnosis.normal) {
    status = calcAIstatus(diagnosis.diseases);
  } else {
    status = "좋음";
  }

  // 상태에 따른 코멘트 함수
  const comment = getComment(status);

  // status에 따라 글자 색 바꿈
  const statusClass = getStatusColor(status);

  return (
    <div
      className="p-4 bg-white rounded-lg shadow-md mb-4 py-4 flex items-center justify-between"
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
