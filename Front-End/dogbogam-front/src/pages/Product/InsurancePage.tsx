import { TopBar } from "../../components/Topbar/index";
import SearchBar from "../../components/SearchBar/index";
import { useEffect, useState } from "react";
import useUserStore from "../../store/UseUserStore";
import { getInsuranceList } from "../../api/insuranceAPI";
import { Insurance } from "../../models/insurance.model";


export const InsurancePage = () => {
  const { dogInfo } = useUserStore();

  const [insuranceList, setInsuranceList] = useState<Insurance[]>([]);
  const [page, setPage] = useState(1);
  const [size] = useState(4);
  
  const fetchInsuranceList = async (page: number, size: number) => {
    try {
      const data = await getInsuranceList(size,page);
      const insuranceArray = Object.values(data).map((item: any) => item.insurance);  // 보험 객체만 추출
      console.log("insuranceArray", insuranceArray);
      setInsuranceList(insuranceArray);
      console.log("insuranceList", insuranceList);
    } catch (error) {
      console.log("보험 리스트를 가져오는 중 오류 발생", error);
    }
  };

  const [selectedButton, setSelectedButton] = useState<string | null>(null);
  
  const handleButton = (buttonType: string) => {
    setSelectedButton(buttonType);
  };

  const handleSearch = (query: string) => {
    console.log("검색어", query);
  };

  useEffect(() => {
    fetchInsuranceList(page, size);
  }, [page, size]);

   // 페이지 변경 함수
   const handlePageChange = (newPage: number) => {
    setPage(newPage);
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <TopBar pre={"/home"} title={""} skip={""} />
        <div className="mb-6">
          <p className="text-gray-800 text-xl font-bold">
            <span className="text-main-color">{`${dogInfo.name}`}</span>
            를 위한, <br /> 보험
          </p>
        </div>
        <div className="mb-6">
          <SearchBar onSearch={handleSearch} />
        </div>
        <div className=" gap-2 mb-6 flex space-x-4 ">
          <div
            className={`${
              selectedButton === "치아"
                ? "bg-main-color text-gray-0"
                : "border-gray-100"
            } rounded-lg shadow-lg p-4 w-13 h-13}`}
            onClick={() => handleButton("치아")}
          >
            <p className="font-medium text-center">치아</p>
          </div>
          <div
            className={`${
              selectedButton === "안구"
                ? "bg-main-color text-gray-0"
                : "border-gray-100"
            } rounded-lg shadow-lg p-4 w-13 h-13}`}
            onClick={() => handleButton("안구")}
          >
            <p className="font-medium text-center">안구</p>
          </div>
          <div
            className={`${
              selectedButton === "관절"
                ? "bg-main-color text-gray-0"
                : "border-gray-100"
            } rounded-lg shadow-lg p-4 w-13 h-13}`}
            onClick={() => handleButton("관절")}
          >
            <p className="font-medium text-center">관절</p>
          </div>
          <div
            className={`${
              selectedButton === "피부"
                ? "bg-main-color text-gray-0"
                : "border-gray-100"
            } rounded-lg shadow-lg p-4 w-13 h-13}`}
            onClick={() => handleButton("피부")}
          >
            <p className="font-medium text-center">피부</p>
          </div>
        </div>
        <div className="grid grid-cols-2 gap-4">
          {insuranceList.map((insurance, index) => (
            <div key={index} className="border border-gray-100 rounded-lg shadow-lg flex justify-between p-5">
            <div className="space-y-5">
              <img
                src={insurance.image_url}
                alt=""
                className="w-[120px] h-[83px]"
              />
              <div className="space-y-1">
                <p className="font-semibold">{insurance.name}</p>
                <p className="text-gray-500 text-sm">
                  {`${insurance.fee}`}원
                </p>
              </div>
            </div>
          </div>
          ))}
        </div>
      </div>
    </div>
  );
};
