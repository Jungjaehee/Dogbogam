import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/MyPage/BackButton.png";

// interface MyInsurance {
//   insurances: {
//     insuranceRecord: {
//       insuranceRecordId: number;
//       insuranceId: number;
//       dogId: number;
//       registDate: string;
//       monthlyPayment: number;
//       expirationDate: string;
//       isDeleted: boolean;
//       createdAt: Date;
//       modifiedAt?: Date | null;
//     };
//     insuranceDetails: {
//       name: string;
//       premium: number;
//       description: string;
//       coveragePeriod: string;
//       insuranceCompany: string;
//     };
//   }[];
// }

const RegistInsurance = () => {
  const navigate = useNavigate();

  // 내 보험 등록 요청 함수
  const ClickSubmitButton = () => {
    // api 연결 필요
  };

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
      <h1 className="text-xl text-gray-700 font-semibold mb-2">
        보험 정보 등록
      </h1>
      <p className="text-gray-500 text-sm mb-2.5">
        마이 펫 보험 정보를 등록하고 간편하게 확인하세요
      </p>

      {/* 보험 입력 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          보험<span className="text-main-color ml-0.5">*</span>
        </label>
        <input
          type="text"
          className="w-full py-2 px-3 border border-gray-100 rounded-md text-gray-700 placeholder:text-sm"
          placeholder="보험을 선택해주세요"
          required
        />
      </div>

      {/* 월 납입료 입력 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          월 납입료<span className="text-main-color ml-0.5">*</span>
        </label>
        <input
          type="text"
          className="w-full py-2 px-3 border border-gray-100 rounded-md text-gray-700 placeholder:text-sm"
          placeholder="월 납입료를 입력해주세요"
          required
        />
      </div>
      {/* 가입 일 만기 일 선택 */}
      <div className="space-y-4">
        <div>
          <label className="block text-gray-700 text-sm font-semibold mb-1">
            가입 일<span className="text-main-color ml-0.5">*</span>
          </label>
          <input
            type="date"
            className="w-full py-2 px-3 border rounded-md text-gray-700"
            required
          />
        </div>

        <div>
          <label className="block text-gray-700 text-sm font-semibold mb-1">
            만기 일<span className="text-main-color ml-0.5">*</span>
          </label>
          <input
            type="date"
            className="w-full py-2 px-3 border rounded-md text-gray-700"
            required
          />
        </div>
      </div>

      {/* 등록 버튼 */}
      <div className="mt-4">
        <button
          className="w-full bg-yellow-400 text-white font-semibold py-3 rounded-lg shadow-md"
          onClick={ClickSubmitButton}
        >
          등록 하기
        </button>
      </div>
    </div>
  );
};

export default RegistInsurance;
