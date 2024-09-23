import { faArrowRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export const RecommendNutrient = () => {
  return (
    <div>
      <p className="font-semibold mb-2">
        새우 맞춤형 <span className="text-main-color">보험</span>에 들어보세요
      </p>
      <p className="text-gray-400 font-medium text-sm mb-5">
        AI 리포트를 기반으로 한 추천 보험이에요
      </p>
      <div className="border border-gray-200 rounded-lg shadow-lg flex justify-between place-items-end p-5">
        <div className="flex space-x-5">
          <img src="" alt="" className="w-[15px] h-[15px]" />
          <div className="space-y-1">
            <p className="font-semibold">삼성화재 반려견</p>
            <p className="text-gray-500 text-sm">13,332원</p>
          </div>
        </div>
        <FontAwesomeIcon icon={faArrowRight} />
      </div>
    </div>
  );
};
