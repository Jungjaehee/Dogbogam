import { useState } from "react";
import { faArrowRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import useUserStore from "../../../store/UseUserStore";
import {
  recommendSupplement,
  supplement,
} from "../../../models/supplement.model";
import { getSupplement } from "../../../api/supplementAPI";
import SupplementDetailModal from "../../Product/components/SupplementDetailModal";

export const RecommendNutrient = (props: { nutrient: recommendSupplement }) => {
  const { dogInfo } = useUserStore();
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달 상태 추가
  const [nutrientProps, setNutrientProps] = useState<supplement | null>(null); // 영양제 정보
  const [isLoading, setIsLoading] = useState(false); // 로딩 상태

  const handleCardClick = async () => {
    setIsLoading(true); // 로딩 시작
    try {
      const responseData = await getSupplement(props.nutrient.supplementId); // 영양제 정보를 가져오기
      console.log(responseData);
      setNutrientProps(responseData); // 영양제 정보 설정
      setIsModalOpen(true); // 모달 열기
    } catch (error) {
      console.error("Error fetching supplement details:", error);
    } finally {
      setIsLoading(false); // 로딩 종료
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false); // 모달 닫기
    setNutrientProps(null); // 영양제 정보 초기화
  };

  return (
    <div>
      <p className="font-semibold mb-2">
        {dogInfo.name} 맞춤형 <span className="text-[#60BF81]">영양제</span>가
        필요하신가요?
      </p>
      <p className="text-gray-400 font-medium text-sm mb-5">
        AI 리포트를 기반으로 한 추천 영양제에요
      </p>
      <div
        className="border border-gray-200 rounded-lg shadow-lg flex justify-between place-items-end p-5"
        onClick={handleCardClick} // 카드 클릭 시 handleCardClick 호출
        style={{ cursor: "pointer" }}
      >
        {props.nutrient.imageUrl === "" ? (
          // 영양제가 없을 경우
          <div className="flex justify-center items-center w-full">
            <p className="text-gray-500 text-md">맞춤 영양제가 없어요</p>
          </div>
        ) : (
          // 영양제가 있을 경우
          <>
            <div className="flex space-x-5 place-items-center">
              <img src={props.nutrient.imageUrl} alt="" className="h-[70px]" />
              <div className="space-y-1">
                <p className="font-semibold">{props.nutrient.productName}</p>
                <p className="text-gray-500 text-sm">
                  {props.nutrient.price.toLocaleString()}원
                </p>
              </div>
            </div>
            <FontAwesomeIcon icon={faArrowRight} />
          </>
        )}
      </div>

      {/* 로딩 중 상태 표시 */}
      {isLoading && <p>Loading...</p>}

      {/* 모달 컴포넌트 렌더링 */}
      {isModalOpen && nutrientProps && (
        <SupplementDetailModal
          supplementDetail={nutrientProps} // 선택된 영양제 정보 전달
          onClose={handleCloseModal} // 모달 닫기 함수 전달
        />
      )}
    </div>
  );
};
