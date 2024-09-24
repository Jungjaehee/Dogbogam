import React from "react";
import DefaultDog from "../../../assets/icons/defaultDog.png";


// DogCard 컴포넌트
interface DogCardProps {
  imageUrl?: string;  // 이미지 경로, 기본값 사용 가능
  dogName: string;       // 강아지 이름
}

export const DogCard: React.FC<DogCardProps> = ({
  imageUrl = DefaultDog, // 이미지가 없으면 DefaultDog 이미지 사용
  dogName,
}) => {
  return (
    <div className="flex items-center space-x-2 p-2">
      {/* 강아지 이미지 (원형) */}
      <div className="w-8 h-8 rounded-full overflow-hidden bg-main-color flex justify-center items-center">
        <img src={imageUrl} alt={`${dogName} 이미지`} className="object-cover w-full h-full" />
      </div>

      {/* 강아지 이름 */}
      <div className="flex-1">
        <p className="text-lg font-semibold">{dogName}</p>
      </div>

    </div>
  );
};
