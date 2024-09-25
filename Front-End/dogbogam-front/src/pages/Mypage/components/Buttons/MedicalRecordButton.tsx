
import MedicalRecordIcon from "../../../../assets/MyPage/MedicalRecordIcon.png";
import { useNavigate } from "react-router-dom";

const MedicalRecordButton = () => {

  const navigate = useNavigate();

  return (
    
    <button
      className="flex flex-col items-center justify-center my-5 p-4 bg-gray-100 rounded-lg shadow-md w-full max-w-sm"
      onClick={() => navigate("medical-record")}
    >
      {/* 아이콘 */}
      <img
        src={MedicalRecordIcon}
        alt="Medical Icon"
        className="w-8 h-8 mb-2"
      />

      {/* 텍스트 */}
      <span className="text-lg font-semibold text-gray-700">
        의료기록 관리하기
      </span>
    </button>
  );
};

export default MedicalRecordButton;
