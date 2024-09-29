import React from "react";
import { DogInfo } from "../../../store/UseDogStore";

// DogCard 컴포넌트
interface DogCardProps {
  dog: Partial<DogInfo>;
}

export const DogCard: React.FC<DogCardProps> = ({
  dog
}) => {
  return (
    <div className="flex items-center space-x-2 p-2">
      {/* 강아지 이미지 (원형) */}
      <div className="w-8 h-8 rounded-full overflow-hidden bg-main-color flex justify-center items-center">
        <img src={dog.imageUrl} alt={`${dog.name} 이미지`} className="object-cover w-full h-full" />
      </div>

      {/* 강아지 이름 */}
      <div className="flex-1">
        <p className="text-lg font-semibold">{dog.name}</p>
      </div>

    </div>
  );
};
