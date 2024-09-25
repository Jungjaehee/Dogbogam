import InsuranceItem from "./InsuranceItem";
import { useNavigate } from "react-router-dom";
import NonInsuranceIcon from "../../../assets/MyPage/NonInsuranceIcon.png";
import RegistIcon from "../../../assets/MyPage/RegistIcon.png";

interface MyInsuranceList {
  insurances: {
    insuranceRecord: {
      insuranceRecordId: number;
      insuranceId: number;
      dogId: number;
      registDate: string;
      monthlyPayment: number;
      expirationDate: string;
      isDeleted: boolean;
      createdAt: Date;
      modifiedAt?: Date | null;
    };
    insuranceDetails: {
      name: string;
      premium: number;
      description: string;
      coveragePeriod: string;
      insuranceCompany: string;
    };
  }[];
}

const InsuranceList = ({ insurances }: MyInsuranceList) => {
    const navigate = useNavigate();

    const ClickRegistButton = () => {
      navigate("/mypage/regist-insurance");
    };

  return (
    <div>
      {insurances.length > 0 ? (
        // 가입 보험이 있을 때
        <>
          {insurances.map((insurance) => (
            <InsuranceItem
              key={insurance.insuranceRecord.insuranceRecordId}
              insuranceRecord={insurance.insuranceRecord}
              insuranceDetails={insurance.insuranceDetails}
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
