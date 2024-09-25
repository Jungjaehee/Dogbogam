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

  // 가입 날짜를 기준으로 납입 횟수 계산
  const recordDate = new Date(insuranceRecord.registDate);
  const paymentsTime = calPaymentsTime(recordDate);

  return (
    <div
      className="p-4 bg-white rounded-lg shadow-md mb-4 py-6 cursor-pointer flex justify-between items-center"
      onClick={ClickInsurance}
    >
      {/* 보험 이름 */}
      <div>
        <span className="text-gray-700 text-ms font-semibold">
          {insuranceDetails.name}
        </span>
      </div>

      {/* 납입 횟수 */}
      <div>
        <span className="text-gray-500 text-xs">
          {paymentsTime}회 납입
        </span>
      </div>
    </div>
  );
};

export default InsuranceItem;
