import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { TopBar } from "../../components/Topbar"
import { registeInsurance } from "../../api/myPetInsuranceAPI"; // 보험 등록 API 함수 임포트
import useUserStore from "../../store/UseUserStore"; // zustand 스토어에서 강아지 정보와 토큰 가져오기
import { getInsuranceList } from "../../api/insuranceAPI";
// import type { insuranceResponse } from "../../models/insurance.model";

const RegistInsurance = () => {
  const navigate = useNavigate();
  const { dogInfo } = useUserStore(); // 강아지 정보와 토큰 가져오기

  // 입력값 상태 관리
  const [insuranceList, setInsuranceList] = useState({});
  const [insuranceId, setInsuranceId] = useState("");
  const [monthlyPayment, setMonthlyPayment] = useState("");
  const [registDate, setRegistDate] = useState("");
  const [expirationDate, setExpirationDate] = useState("");

  useEffect(() => {
    const fetchInsuranceList = async () => {
      try {
        const data = await getInsuranceList(); // 보험 리스트 API 호출
        // 가져온 데이터를 상태에 저장
        setInsuranceList(data);
        console.log(data)
      } catch (error) {
        console.error("보험 리스트를 불러오는 중 에러 발생:", error);
      }
    };
    
    fetchInsuranceList();
  }, []);

  const today = new Date().toISOString().split("T")[0];
  // 보험 등록 요청 함수
  const ClickSubmitButton = async () => {
    if (!insuranceId || !monthlyPayment || !registDate || !expirationDate) {
      alert("모든 필드를 입력해 주세요.");
      return;
    }

    const insuranceRecord = {
      insuranceId: Number(insuranceId), // 보험 ID
      dogId: dogInfo.dogId, // 강아지 ID
      registDate: new Date(registDate), 
      monthlyPayment: Number(monthlyPayment), // 월 납입료
      expirationDate: new Date(expirationDate), 
    };

    try {
      const response = await registeInsurance(insuranceRecord); // API 호출
      console.log("보험 등록 성공:", response);
      
      navigate(-1); 
    } catch (error) {
      console.error("보험 등록 실패:", error);
      
    }
  };
  
  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
      <TopBar pre={""} title={""} skip={""} />
      {/* 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">
        보험 정보 등록
      </h1>
      <p className="text-gray-500 text-sm mb-2.5">
        마이 펫 보험 정보를 등록하고 간편하게 확인하세요
      </p>

      {/* 보험 선택 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          보험<span className="text-main-color ml-0.5">*</span>
        </label>
        <select
          className="w-full py-2 px-3 border border-gray-100 rounded-md text-gray-700 text-sm"
          value={insuranceId}
          onChange={(e) => setInsuranceId(e.target.value)} // 보험 ID 업데이트
        >
          <option value="">보험을 선택해주세요</option>
          {Object.entries(insuranceList).map(([key, value]: any) => (
            <option key={key} value={value.insurance.insuranceId}>
              {value.insurance.name}
            </option>
          ))}
        </select>
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
          value={monthlyPayment}
          onChange={(e) => setMonthlyPayment(e.target.value)}
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
            value={registDate}
            onChange={(e) => setRegistDate(e.target.value)}
            max={today}
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
            value={expirationDate}
            onChange={(e) => setExpirationDate(e.target.value)}
            min={today}
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
