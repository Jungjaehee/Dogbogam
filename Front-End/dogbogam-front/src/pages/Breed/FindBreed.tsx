import React from "react";
import FindDogLoading from "../../assets/images/findDogLoading.png";
import { TopBar } from "../../components/Topbar";
// import useUserStore from "../../store/UseUserStore";
import { Button } from "../../components/Button";



export const FindBreed = () => {

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col w-full">
      <TopBar pre={""} title={""} skip={""} />
     
      <div className="space-y-3 mb-20">
        <p className="text-xl font-semibold">
          <span>재미로 보는, <br /> 우리 아이 </span>
          <span className="text-main-color">견종 찾기</span>
        </p>
        <p className="text-gray-400 font-medium text-sm">
          AI가 견종을 판독해드려요
        </p>
      </div>
      <img src={FindDogLoading} alt="" className="w-[328px] h-[328px] mb-20" />
      <Button
          text={"다음"}
          onClick={() => {
          }}
          bgColor={"bg-main-color"}
        />
    </div>
  );
};
