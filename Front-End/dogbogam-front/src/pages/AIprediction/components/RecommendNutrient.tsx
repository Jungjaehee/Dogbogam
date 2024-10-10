import { faArrowRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import useUserStore from "../../../store/UseUserStore";
import { recommendSupplement } from "../../../models/supplement.model";

export const RecommendNutrient = (props: { nutrient: recommendSupplement }) => {
  const { dogInfo } = useUserStore();
  return (
    <div>
      <p className="font-semibold mb-2">
        {dogInfo.name} 맞춤형 <span className="text-[#60BF81]">영양제</span>가
        필요하신가요?
      </p>
      <p className="text-gray-400 font-medium text-sm mb-5">
        AI 리포트를 기반으로 한 추천 영양제에요
      </p>
      <div className="border border-gray-200 rounded-lg shadow-lg flex justify-between place-items-end p-5">
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
    </div>
  );
};
