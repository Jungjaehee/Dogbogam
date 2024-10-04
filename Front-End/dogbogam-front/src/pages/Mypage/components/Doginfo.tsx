import { calAge } from "../../../utils/calcDate";
import GenderIcon from "./GenderIcon";
import UpdateIcon from "../../../assets/MyPage/InfoUpdateIcon.png";
import type { Dog } from "../../../models/dog.model"

const DogInfo = ({ dog }: { dog: Dog }) => {
  const { name, breed, gender, birth, weight, imageUrl } = dog;
  const age = birth ? calAge(birth) : "나이 모름";
  return (
    <div className="flex items-center p-4 bg-white rounded-lg shadow-md w-full max-w-sm">
      {/* 강아지 프사 */}
      <img
        src={imageUrl || "https://via.placeholder.com/64"}
        alt={`${name}의 프로필 이미지`}
        className="w-14 h-14 rounded-full object-cover"
      />

      {/* 강아지 개인정보 */}
      <div className="ml-4 flex-1">
        <div className="flex items-center justify-between">
          
          <div className="flex items-center">
            <h2 className="text-lg font-bold">{name}</h2>
            <GenderIcon gender={gender} className="ml-1.5" />
          </div>

          {/* 정보 수정 버튼 */}
          <button>
            <img src={UpdateIcon} alt="Update Icon" className="w-3.5 h-3.5" />
          </button>
        </div>

        <p className="text-gray-500">
          {breed} · {age}세 {weight ? `${weight}kg` : "몸무게 모름"}
        </p>
      </div>
    </div>
  );
};

export default DogInfo;
