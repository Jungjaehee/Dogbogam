import { faArrowRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { recommendInsurance } from "../../../models/insurance.model";
import useUserStore from "../../../store/UseUserStore";

export const RecommendInsurance = (props: {
  insurance: recommendInsurance;
}) => {
  const { dogInfo } = useUserStore();
  return (
    <div>
      <p className="font-semibold mb-2">
        {dogInfo.name} 맞춤형 <span className="text-main-color">보험</span>에
        들어보세요
      </p>
      <p className="text-gray-400 font-medium text-sm mb-5">
        AI 리포트를 기반으로 한 추천 보험이에요
      </p>
      <div className="border border-gray-200 rounded-lg shadow-lg flex justify-between place-items-end p-5">
        <div className="flex space-x-5 place-items-center">
          <img
            src={props.insurance.image}
            alt=""
            className="w-[25px] h-[25px]"
          />
          <div className="space-y-1">
            <p className="font-semibold">{props.insurance.name}</p>
            <p className="text-gray-500 text-sm">{props.insurance.fee}</p>
          </div>
        </div>
        <FontAwesomeIcon icon={faArrowRight} />
      </div>
    </div>
  );
};
