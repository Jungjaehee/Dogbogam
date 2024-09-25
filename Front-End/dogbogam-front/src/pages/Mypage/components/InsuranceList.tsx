import MedicalRecordItem from "./MedicalRecordItem";
import NonMedicalRecordIcon from "../../../assets/MyPage/NonMedicalRecordIcon.png";
import RegistIcon from "../../../assets/MyPage/RegistIcon.png";
// import { useNavigate } from "react-router-dom";

interface MyInsuranceList {
  insurances: {
    insuranceId: number; // 보험 아이디
    name: string; // 보험 이름
    minEntryAge?: string | null; // 가입 최소 나이
    maxEntryAge?: string | null; // 가입 최대 나이
    premium: number; // 납입료
    coveragePeriod: string; // 보장 기간
    description: string; // 설명문
    coverageLimit?: string | null; // 보장금액 한도
    compensationRate: string; // 보상비율
    insuranceCompany: string; // 보험사
    imageName?: string | null; // S3 이미지 이름
    imageUrl?: string | null; // S3 이미지 URL
    createdAt: Date; // 생성 시간
    modifiedAt: Date; // 수정 시간
  }[];
}

const InsuranceList = ({ insurances }: MyInsuranceList) => {
  //   const navigate = useNavigate();

  //   const ClickRegistButton = () => {
  //     navigate("/regist-record");
  //   };

  return (
    <div>
      {insurances.length > 0 ? (
        // 진료 기록이 있을 때
        <>
          {insurances.map((insurance) => (
            <MedicalRecordItem
              key={insurance.insuranceId.toString()}
              insurances={insurance}
            />
          ))}
          {/* 진료 기록 등록 버튼 */}
          <div className="flex justify-center mt-6">
            <button
            //   onClick={ClickRegistButton}
              className="flex items-center space-x-2"
            >
              <img
                src={RegistIcon}
                alt="Add Medical Record Icon"
                className="w-5 h-5"
              />
              <span className="text-gray-500 font-semibold text-sm">
                진료 기록 등록
              </span>
            </button>
          </div>
        </>
      ) : (
        // 진료 기록이 없을 때
        <div className="flex flex-col items-center justify-center h-[calc(100vh-200px)]">
          <img
            src={NonMedicalRecordIcon}
            alt="NonMedicalRecord Icon"
            className="w-18 h-18 mb-4"
          />
          <span className="text-md text-gray-500">진료 기록이 없어요.</span>
          <button
            // onClick={ClickRegistButton}
            className="w-3/4 bg-main-color text-gray-0 font-semibold py-2.5 px-10 rounded-lg shadow-md mt-[8vh]"
          >
            진료 기록 등록하기
          </button>
        </div>
      )}
    </div>
  );
};

export default InsuranceList;
