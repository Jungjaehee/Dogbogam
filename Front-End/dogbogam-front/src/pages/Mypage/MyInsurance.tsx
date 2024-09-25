import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/MyPage/BackButton.png";
import InsuranceList from "./components/InsuranceList";


// 더미 데이터

// 데이터 없을 때
// const dummyData: any[] = [];

// 데이터 있을 때
const dummyData = [
  {
    insuranceRecord: {
      insuranceRecordId: 1,
      insuranceId: 1,
      dogId: 101,
      registDate: "2022-01-15",
      monthlyPayment: 30000,
      expirationDate: "2023-01-15",
      isDeleted: false,
      createdAt: new Date("2022-01-15T09:00:00Z"),
      modifiedAt: null,
    },
    insuranceDetails: {
      name: "펫 건강 종합 보험",
      premium: 30000,
      description: "반려견의 종합 건강을 보장하는 보험입니다.",
      coveragePeriod: "1년",
      insuranceCompany: "삼성화재",
    },
  },
  {
    insuranceRecord: {
      insuranceRecordId: 2,
      insuranceId: 2,
      dogId: 102,
      registDate: "2021-05-10",
      monthlyPayment: 25000,
      expirationDate: "2023-05-10",
      isDeleted: false,
      createdAt: new Date("2021-05-10T14:00:00Z"),
      modifiedAt: null,
    },
    insuranceDetails: {
      name: "펫 실비 보험",
      premium: 25000,
      description: "반려견의 질병과 상해를 보장하는 실비 보험입니다.",
      coveragePeriod: "2년",
      insuranceCompany: "현대해상",
    },
  },
  {
    insuranceRecord: {
      insuranceRecordId: 3,
      insuranceId: 3,
      dogId: 103,
      registDate: "2020-03-20",
      monthlyPayment: 40000,
      expirationDate: "2023-03-20",
      isDeleted: false,
      createdAt: new Date("2020-03-20T11:30:00Z"),
      modifiedAt: null,
    },
    insuranceDetails: {
      name: "펫 노령견 보험",
      premium: 40000,
      description: "노령 반려견을 위한 특화된 보험 상품입니다.",
      coveragePeriod: "3년",
      insuranceCompany: "KB손해보험",
    },
  },
];

const MyInsurance = () => {
  const navigate = useNavigate();

  const ClickBackButton = () => {
    navigate(-1);
  };

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
      {/* 뒤로가기 버튼 */}
      <button>
        <img
          src={BackButton}
          alt="Back Button"
          className="w-7 h-7 mb-2.5"
          onClick={ClickBackButton}
        />
      </button>

      {/* 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">마이 펫 보험</h1>
      <p className="text-gray-500 text-sm mb-2.5">
        반려견에게 가입된 보험을 확인할 수 있어요
      </p>

      {/* 보험 목록 */}
      <InsuranceList insurances={dummyData} />
    </div>
  );
};

export default MyInsurance;
