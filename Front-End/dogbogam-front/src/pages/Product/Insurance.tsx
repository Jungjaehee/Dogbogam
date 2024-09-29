import React from "react";
import { TopBar } from "../../components/Topbar/index";
import SearchBar from "../../components/SearchBar/index";
import { useState } from "react";

interface Dog {
  dogId: number;
  dogName: string;
}

interface Supplement {
  supplimentId: number;
  productName: string;
  price: string;
  supplimentUrl: string;
}

export const Insurance = () => {

  const dog: Dog = {
    dogId: 1,
    dogName: "새우",
  }

  const supplement: Supplement = ({
    supplimentId: 1,
    productName: "우프앤먀오",
    price: "35000",
    supplimentUrl: "https://godomall.speedycdn.net/8bb927ea5e884dfc56c94474d6fa9fe1/goods/1000000126/image/main/1000000126_main_048.jpg",
  })  

  const [selectedButton, setSelectedButton] = useState<string|null>(null);

  const handleButton = (buttonType: string) => {
    setSelectedButton(buttonType);
  }

  const handleSearch = (query: string) => {
    console.log("검색어", query);
  }

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <TopBar pre={"/home"} title={""} skip={""} />
        <div className="mb-6">
          <p className="text-gray-800 text-xl font-bold">
            <span className="text-main-color">{`${dog.dogName}`}</span>
            를 위한, <br /> 영양제
          </p>
        </div>
        <div className="mb-6">
          <SearchBar onSearch={handleSearch} />
        </div>
        <div className=" gap-2 mb-6 flex space-x-4 ">
          <div 
            className={`${selectedButton === "치아" ? "bg-main-color text-gray-0" : "border-gray-100"} rounded-lg shadow-lg p-4 w-13 h-13}`}
            onClick={() => handleButton("치아")}
          >
            <p className="font-medium text-center">치아</p>
          </div>
          <div 
            className={`${selectedButton === "안구" ? "bg-main-color text-gray-0" : "border-gray-100"} rounded-lg shadow-lg p-4 w-13 h-13}`}
            onClick={() => handleButton("안구")}
          >
            <p className="font-medium text-center">안구</p>
          </div>
          <div 
            className={`${selectedButton === "관절" ? "bg-main-color text-gray-0" : "border-gray-100"} rounded-lg shadow-lg p-4 w-13 h-13}`}
            onClick={() => handleButton("관절")}
          >
            <p className="font-medium text-center">관절</p>
          </div>
          <div 
            className={`${selectedButton === "피부" ? "bg-main-color text-gray-0" : "border-gray-100"} rounded-lg shadow-lg p-4 w-13 h-13}`}
            onClick={() => handleButton("피부")}
          >
            <p className="font-medium text-center">피부</p>
          </div>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div className="border border-gray-100 rounded-lg shadow-lg flex justify-between p-5">
            <div className="space-y-5">
              <img src={supplement.supplimentUrl} alt="" className="w-[120px] h-[83px]" />
              <div className="space-y-1">
                <p className="font-semibold">{supplement.productName}</p>
                <p className="text-gray-500 text-sm">{`${supplement.price}`}원</p>
              </div>
            </div>
          </div>
          <div className="border border-gray-100 rounded-lg shadow-lg flex justify-between p-5">
            <div className="space-y-5">
              <img src={supplement.supplimentUrl} alt="" className="w-[120px] h-[83px]" />
              <div className="space-y-1">
                <p className="font-semibold">{supplement.productName}</p>
                <p className="text-gray-500 text-sm">{`${supplement.price}`}원</p>
              </div>
            </div>
          </div>
          <div className="border border-gray-100 rounded-lg shadow-lg flex justify-between p-5">
            <div className="space-y-5">
              <img src={supplement.supplimentUrl} alt="" className="w-[120px] h-[83px]" />
              <div className="space-y-1">
                <p className="font-semibold">{supplement.productName}</p>
                <p className="text-gray-500 text-sm">{`${supplement.price}`}원</p>
              </div>
            </div>
          </div>
          <div className="border border-gray-100 rounded-lg shadow-lg flex justify-between p-5">
            <div className="space-y-5">
              <img src={supplement.supplimentUrl} alt="" className="w-[120px] h-[83px]" />
              <div className="space-y-1">
                <p className="font-semibold">{supplement.productName}</p>
                <p className="text-gray-500 text-sm">{`${supplement.price}`}원</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )}