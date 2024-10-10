import { calAge } from "../../../utils/calcDate";
import GenderIcon from "./GenderIcon";
import UpdateIcon from "../../../assets/MyPage/InfoUpdateIcon.png";
import { useNavigate } from "react-router-dom";
import useUserStore from "../../../store/UseUserStore";

const DogInfo = () => {

  const {dogInfo} = useUserStore();

  console.log(typeof(dogInfo.birth))

  const age =
    typeof dogInfo.birth === "string" && dogInfo.birth
      ? calAge(dogInfo.birth)
      : "모름";

  
  const navigate = useNavigate();
  
  const clickUpdateButton = () => {
    navigate("updateInfo")
  }
  return (
    <div className="flex items-center p-4 bg-white rounded-lg shadow-md w-full max-w-sm">
      {/* 강아지 프사 */}
      <img
        src={dogInfo.imageUrl || "https://via.placeholder.com/64"}
        alt={`${dogInfo.name}의 프로필 이미지`}
        className="w-14 h-14 rounded-full object-cover"
      />

      {/* 강아지 개인정보 */}
      <div className="ml-4 flex-1">
        <div className="flex items-center justify-between">
          <div className="flex items-center">
            <h2 className="text-lg font-bold">{dogInfo.name}</h2>
            <GenderIcon gender={dogInfo.gender} className="ml-1.5" />
          </div>

          {/* 정보 수정 버튼 */}
          <button>
            <img
              src={UpdateIcon}
              alt="Update Icon"
              className="w-3.5 h-3.5"
              onClick={clickUpdateButton}
            />
          </button>
        </div>

        <p className="text-gray-500">
          {dogInfo.breed} · {age ? `${age}세` : "나이 모름"} · {dogInfo.weight ? `${dogInfo.weight}kg` : "몸무게 모름"}
        </p>
      </div>
    </div>
  );
};

export default DogInfo;
