import { faArrowRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { recommendInsurance } from "../../../models/insurance.model";
import useUserStore from "../../../store/UseUserStore";
import { useState } from "react"; // useState 추가
import InsuranceDetailModal from "../../Product/components/InsuranceDetailModal"; // 모달 컴포넌트 import
import { Insurance } from "../../../models/insurance.model";
import { getInsurance } from "../../../api/insuranceAPI";

export const RecommendInsurance = (props: {
  insurance: recommendInsurance;
}) => {
  const { dogInfo } = useUserStore();
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달 상태 추가
  const [selectedInsurance, setSelectedInsurance] =
    useState<recommendInsurance | null>(null); // 선택된 보험 정보 상태 추가
  const [insuranceProps, setInsuranceProps] = useState<Insurance | null>(null); // 보험 정보는 null일 수 있으므로 null로 초기화
  const [isLoading, setIsLoading] = useState(false); // 로딩 상태 추가

  const handleCardClick = async () => {
    setIsLoading(true); // 로딩 시작
    const responseData = await getInsurance(props.insurance.insuranceId); // 데이터를 가져오기
    console.log(responseData);
    setInsuranceProps(responseData.insurance); // 데이터 설정
    setSelectedInsurance(props.insurance); // 선택된 보험 정보 저장
    setIsModalOpen(true); // 모달 열기
    setIsLoading(false); // 로딩 종료
  };

  const handleCloseModal = () => {
    setIsModalOpen(false); // 모달 닫기
    setSelectedInsurance(null); // 선택된 보험 정보 초기화
    setInsuranceProps(null); // 보험 정보 초기화
  };

  return (
    <div>
      <p className="font-semibold mb-2">
        {dogInfo.name} 맞춤형 <span className="text-main-color">보험</span>에
        들어보세요
      </p>
      <p className="text-gray-400 font-medium text-sm mb-5">
        AI 리포트를 기반으로 한 추천 보험이에요
      </p>
      <div
        className="border border-gray-200 rounded-lg shadow-lg flex justify-between place-items-end p-5"
        onClick={handleCardClick} // 카드 클릭 시 handleCardClick 호출
        style={{ cursor: "pointer" }}
      >
        <div className="flex space-x-5 place-items-center">
          <img
            src={props.insurance.image}
            alt="보험 이미지"
            className="w-[25px] h-[25px]"
          />
          <div className="space-y-1">
            <p className="font-semibold">{props.insurance.name}</p>
            <p className="text-gray-500 text-sm">{props.insurance.fee}</p>
          </div>
        </div>
        <FontAwesomeIcon icon={faArrowRight} />
      </div>

      {/* 로딩 중 상태 표시 */}
      {isLoading && <p>Loading...</p>}

      {/* 모달 컴포넌트 렌더링 */}
      {isModalOpen && selectedInsurance && insuranceProps && (
        <InsuranceDetailModal
          insuranceDetail={insuranceProps} // 선택된 보험 정보 전달
          onClose={handleCloseModal} // 모달 닫기 함수 전달
        />
      )}
    </div>
  );
};
