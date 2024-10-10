import { useNavigate, useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import { TopBar } from "../../components/Topbar"
import useUserStore from "../../store/UseUserStore";
import { insuranceResponse, insuranceItem } from "../../models/insurance.model";
import {
  getMyInsuranceDetail,
  deleteInsurance,
} from "../../api/myPetInsuranceAPI";
import { getInsurance } from "../../api/insuranceAPI";

const InsuranceDetail = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { id } = location.state;
  const [insuranceDetail, setInsuranceDetail] = useState<insuranceItem | null>(
    null
  );
  const [productDetail, setProductDetail] = useState<insuranceResponse | null>(
    null
  );
  const { dogInfo } = useUserStore();

  const fetchData = async () => {
    const responseData = await getMyInsuranceDetail(id);
    setInsuranceDetail(responseData);
    const responseProduct = await getInsurance(responseData.insuranceId);
    setProductDetail(responseProduct);
  };

  useEffect(() => {
    fetchData();
  }, [id]);

  const handleDeleteClick = async () => {
    await deleteInsurance(id);
    navigate(-1);
  };

  const record = insuranceDetail;
  if (!record) {
    return <p>보험 기록을 불러오고 있어요!</p>;
  }

  // 가입일과 만료일을 Date 객체로 변환
  const registDate = new Date(record.registDate);
  const expirationDate = new Date(record.expirationDate);

  // 가입일부터 만료일까지의 차이를 월 단위로 계산
  const totalMonths =
    (expirationDate.getFullYear() - registDate.getFullYear()) * 12 +
    expirationDate.getMonth() -
    registDate.getMonth();

  // 총 납부 금액 계산 (월 납입료 * 총 개월 수)
  const totalPayment = totalMonths * record.monthlyPayment;
  
  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
      <TopBar pre={""} title={""} skip={""} />
      {/* 강아지의 예방 접종 기록 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">
        <span className="text-yellow-500">{dogInfo.name}</span>의 펫 보험
      </h1>
      <p className="text-gray-500 text-xs mb-2.5">
        펫 보험 가입을 통해 치료비보다 치료에 집중하세요
      </p>

      {/* 보험 요약 (월 보험료, 매달 납입일) */}
      <div className="bg-gray-50 p-4 rounded-xl shadow-md mb-6 flex justify-between items-center">
        <div className="flex flex-col">
          <div className="text-xs text-gray-600">월 보험료</div>
          <div className="text-lg font-bold text-gray-900">
            {record.monthlyPayment.toLocaleString()}원
          </div>
        </div>
        <div className="text-xs text-gray-600 mt-2">
          매달 {new Date(record.registDate).getDate()}일
        </div>
      </div>
      <h3 className="text-lg text-gray-700 font-semibold mb-2">
        {productDetail?.insurance.company}의
      </h3>
      {/* 보험 상세 설명 */}
      <h1 className="text-lg text-gray-700 font-semibold mb-2">
        <span className="text-yellow-500">
          {productDetail?.insurance.name}{" "}
        </span>
        한 줄 요약
      </h1>
      <p className="text-gray-500 text-xs mb-2.5">
        {productDetail?.insurance.description}
      </p>

      {/* 보험 세부 사항 (회사명, 이미지, 가입일, 만료일, 총 납부 금액) */}
      <div className="mb-4 bg-gray-100 p-4 rounded-lg shadow-md">
        {/* 총 납부 금액 계산 */}
        <div className="mb-2">
          <label className="text-xs text-gray-600">
            만기일 기준 총 납입 금액은
          </label>
          <p className="text-lg font-bold text-gray-900">
            {totalPayment.toLocaleString()} 원이에요
          </p>
        </div>
        <div className="mb-2">
          <label className="text-xs text-gray-600">보장 갯수는</label>
          <p className="text-lg font-bold text-gray-900">
            총 {productDetail?.benefit.length}개에요
          </p>
        </div>

        {/* benefit 배열을 순회하며 렌더링 */}
        <div className="mb-2">
          <label className="text-xs text-gray-600">보장 항목</label>
          <ul className="list-disc pl-5">
            {productDetail?.benefit.map((item, index) => (
              <li key={index} className="text-sm text-gray-600 mb-1">
                {item}
              </li>
            ))}
          </ul>
        </div>
      </div>

      {/* 삭제 버튼 */}
      <button
        className="w-full bg-yellow-400 text-white font-semibold py-3 rounded-lg shadow-md mt-4"
        onClick={() => handleDeleteClick()}
      >
        내역 삭제
      </button>
    </div>
  );
};

export default InsuranceDetail;
