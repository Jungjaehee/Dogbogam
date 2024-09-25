import { useNavigate } from "react-router-dom";
import { calPaymentsTime } from "../../../utils/calcDate"; 

interface MyInsurance {
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
}

const InsuranceItem = ({ insuranceRecord, insuranceDetails }: MyInsurance) => {
  const navigate = useNavigate();

  
  const ClickInsurance = () => {
    navigate(`${insuranceRecord.insuranceId}`);
  };

  // 가입 날짜를 기준으로 상대 시간 계산
  const recordDate = new Date(insuranceRecord.registDate);
  const paymentsTime = calPaymentsTime(recordDate);

  return (
    <div
      className="p-4 bg-white rounded-lg shadow-md mb-4 cursor-pointer"
      onClick={ClickInsurance}
    >
      <div className="flex justify-between items-center">
        {/* 보험 이름 */}
        <div className="flex flex-col">
          <span className="text-gray-700 font-bold">
            {insuranceDetails.name}
          </span>
        </div>
      </div>

      {/* 보험 설명 및 월 납입료 */}
      <div className="mt-2">
        <p className="text-gray-500">{paymentsTime}회 납입</p>
      </div>
    </div>
  );
};

export default InsuranceItem;
