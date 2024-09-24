import { useNavigate } from "react-router-dom";
import AiDiagnosisIcon from "../../../../assets/AIDiagnosisIcon.png";

const AIDiagnosisButton = () => {
  const navigate = useNavigate();

  return (
    <button
      className="w-full bg-ai-button p-4 rounded-lg shadow-md flex flex-col items-center justify-center"
      onClick={() => navigate("ai-diagnosis")}
    >
      <img src={AiDiagnosisIcon} alt="Calendar Icon" className="w-6 h-6 mr-2" />
      <span className="text-lg font-semibold text-gray-700">AI 진단 기록</span>
    </button>
  );
};

export default AIDiagnosisButton;
