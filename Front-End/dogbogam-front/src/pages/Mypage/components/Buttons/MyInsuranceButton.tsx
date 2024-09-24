import { useNavigate } from "react-router-dom";
import MyInsuranceIcon from "../../../../assets/MyPage/MyInsuranceIcon.png";

const MyInsuranceButton = () => {
  const navigate = useNavigate();

  return (
    <button
      className="w-full bg-insu-button p-4 rounded-lg shadow-md flex items-center flex-col justify-center"
      onClick={() => navigate("my-insurance")}
    >
      <img src={MyInsuranceIcon} alt="Calendar Icon" className="w-6 h-6 mr-2" />
      <span className="text-lg font-semibold text-gray-700">마이 펫 보험</span>
    </button>
  );
};

export default MyInsuranceButton;
