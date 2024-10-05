import { useEffect , useState } from "react";
import InsuranceItem from "./InsuranceItem";
import { useNavigate } from "react-router-dom";
import NonInsuranceIcon from "../../../assets/MyPage/NonInsuranceIcon.png";
import RegistIcon from "../../../assets/MyPage/RegistIcon.png";
import useInsuranceStore from "../../../store/UseInsuranceStore";
import type { myInsurance } from "../../../models/insurance.model";

const InsuranceList = () => {
  const navigate = useNavigate();

  const { insuranceList } = useInsuranceStore(); // zustand 스토어에서 전역 상태 가져오기
  const [insuranceArray, setInsuranceArray] = useState<myInsurance[]>([]); // 로컬 상태 관리

  useEffect(() => {
    const data = localStorage.getItem("insuranceStorage");
    if (data) {
      const parsedData = JSON.parse(data);
      const storedInsuranceArray =
        parsedData.state.insuranceList.data.insuranceRecords;
      setInsuranceArray(storedInsuranceArray);
    } else {
      setInsuranceArray(insuranceList); // 스토어에서 가져온 데이터를 로컬 상태에 반영
    }
  }, [insuranceList]); // 진단 리스트 변경 시 다시 반영

  const ClickRegistButton = () => {
    navigate("/mypage/regist-insurance");
  };

  return (
    <div>
      {insuranceArray.length > 0 ? (
        // 가입 보험이 있을 때
        <>
          {insuranceArray.map((insurance) => (
            <InsuranceItem
              key={insurance.insuranceId}
              insurance={insurance}
            />
          ))}
          {/* 보험 추가 가입? 조회? 버튼 */}
          <div className="flex justify-center mt-6">
            <button
              onClick={ClickRegistButton}
              className="flex items-center space-x-2"
            >
              <img
                src={RegistIcon}
                alt="Add Medical Record Icon"
                className="w-5 h-5"
              />
              <span className="text-gray-500 font-semibold text-sm">
                보험 추가 가입 하기
              </span>
            </button>
          </div>
        </>
      ) : (
        // 가입 보험이 없을 때
        <div className="flex flex-col items-center justify-center h-[calc(100vh-200px)]">
          <img
            src={NonInsuranceIcon}
            alt="NonInsuranceIcon Icon"
            className="w-18 h-18 mb-4"
          />
          <span className="text-md text-gray-500">마이 펫 보험이 없어요.</span>
          <button
            onClick={ClickRegistButton}
            className="w-3/4 bg-main-color text-gray-0 font-semibold py-2.5 px-10 rounded-lg shadow-md mt-[8vh]"
          >
            마이 펫 보험 등록 하기
          </button>
        </div>
      )}
    </div>
  );
};

export default InsuranceList;
