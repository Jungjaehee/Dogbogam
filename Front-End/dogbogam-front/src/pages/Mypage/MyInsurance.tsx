import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/MyPage/BackButton.png";
import InsuranceList from "./components/InsuranceList";

// const dummyData: any[] = [];

const dummyData = [
  {
    insuranceId: 1,
    name: "펫 건강 종합 보험",
    minEntryAge: "1세",
    maxEntryAge: "10세",
    premium: 30000,
    coveragePeriod: "1년",
    description: "반려견의 종합 건강을 보장하는 보험입니다.",
    coverageLimit: "5,000,000원",
    compensationRate: "80%",
    insuranceCompany: "삼성화재",
    imageName: "pet_health_insurance.jpg",
    imageUrl: "https://example.com/pet_health_insurance.jpg",
    createdAt: new Date("2022-01-15T09:00:00Z"),
    modifiedAt: new Date("2023-01-01T10:00:00Z"),
  },
  {
    insuranceId: 2,
    name: "펫 실비 보험",
    minEntryAge: "3세",
    maxEntryAge: "12세",
    premium: 25000,
    coveragePeriod: "2년",
    description: "반려견의 질병과 상해를 보장하는 실비 보험입니다.",
    coverageLimit: "3,000,000원",
    compensationRate: "70%",
    insuranceCompany: "현대해상",
    imageName: "pet_silbi_insurance.jpg",
    imageUrl: "https://example.com/pet_silbi_insurance.jpg",
    createdAt: new Date("2021-05-10T14:00:00Z"),
    modifiedAt: new Date("2022-06-10T10:00:00Z"),
  },
  {
    insuranceId: 3,
    name: "펫 노령견 보험",
    minEntryAge: "8세",
    maxEntryAge: "15세",
    premium: 40000,
    coveragePeriod: "3년",
    description: "노령 반려견을 위한 특화된 보험 상품입니다.",
    coverageLimit: "10,000,000원",
    compensationRate: "90%",
    insuranceCompany: "KB손해보험",
    imageName: "pet_senior_insurance.jpg",
    imageUrl: "https://example.com/pet_senior_insurance.jpg",
    createdAt: new Date("2020-03-20T11:30:00Z"),
    modifiedAt: new Date("2021-03-20T09:00:00Z"),
  },
];

const MedicalRecord = () => {
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

      {/* 진료 기록 리스트 */}
      <InsuranceList insurances={dummyData} />
    </div>
  );
};

export default MedicalRecord;
