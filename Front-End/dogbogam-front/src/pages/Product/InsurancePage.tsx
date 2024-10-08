import { TopBar } from "../../components/Topbar/index";
import SearchBar from "../../components/SearchBar/index";
import { useEffect, useState } from "react";
import useUserStore from "../../store/UseUserStore";
import { getInsuranceList } from "../../api/insuranceAPI";
import { getInsurance } from "../../api/insuranceAPI";
import { Insurance } from "../../models/insurance.model";
import InsuranceDetailModal from "./components/InsuranceDetailModal";

export const InsurancePage = () => {
  const { dogInfo } = useUserStore();

  const [insuranceList, setInsuranceList] = useState<{ insurance: Insurance, benefit: string[] }[]>([]);
  const [selectedInsurance, setSelectedInsurance] = useState<Insurance | null>(null);
  const [selectedButton, setSelectedButton] = useState<string | null>(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);

  // 보험 리스트 가져오기
  const fetchInsuranceList = async () => {
    try {
      const data = await getInsuranceList();
      // 만약 data가 객체 형태로 오면 Object.values로 배열로 변환
      const insuranceArray = Object.values(data) as { insurance: Insurance; benefit: string[] }[];
      console.log("insuranceArray", insuranceArray);
      setInsuranceList(insuranceArray);
    } catch (error) {
      console.log("보험 리스트를 가져오는 중 오류 발생", error);
    }
  };

  // 보험 상세 정보 가져오기
  const fetchInsurance = async (insuranceId: number) => {
    try {
      const response = await getInsurance(insuranceId);
      const insuranceDetail = response.insurance;
      console.log("insurance", insuranceDetail); // 수정된 출력
      setSelectedInsurance(insuranceDetail); // 상세 정보 상태로 저장
      setIsModalOpen(true); // 모달 열기
    } catch (error) {
      console.log("보험 상세 정보 가져오기 실패", error);
      return null;
    }
  };
  
  // 컴포넌트가 마운트될 때 보험 리스트 가져오기
  useEffect(() => {
    fetchInsuranceList();
  }, []);

  const searchedInsuranceList = insuranceList.filter(item =>
    item.insurance.name.includes(searchQuery)
  );

  const selectedInsuranceList = searchedInsuranceList.filter(item =>
    selectedButton ? item.benefit.some(benefit => benefit.includes(selectedButton)) : true
  );

  const handleButton = (buttonType: string) => {
    setSelectedButton(prevButton => (prevButton === buttonType ? null : buttonType));
  };

  const handleSearch = (query: string) => {
    setSearchQuery(query); // 검색어 설정
  };

  const handleCardClick = async (insuranceId: number) => {
    const insuranceDetail = await fetchInsurance(insuranceId);
    if (insuranceDetail) {
      setSelectedInsurance(insuranceDetail);  // 상세 정보를 상태로 저장
      setIsModalOpen(true); // 모달 열기
    }
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedInsurance(null);
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <TopBar pre={"/home"} title={""} skip={""} />
        <p className="text-gray-800 text-xl font-bold mb-3">
          <span className="text-main-color">{`${dogInfo.name}`}</span>
          를 위한, <br /> 보험
        </p>
        <SearchBar onSearch={handleSearch} />
        <div className="gap-4 flex mt-4 mb-4">
          <div
            className={`${
              selectedButton === "질환"
                ? "bg-main-color text-gray-0"
                : "border-gray-100"
            } flex-grow flex justify-center items-center rounded-lg shadow-md p-4 h-12`}
            onClick={() => handleButton("질환")}
          >
            <p className="font-medium">질환</p>
          </div>
          <div
            className={`${
              selectedButton === "책임"
                ? "bg-main-color text-gray-0"
                : "border-gray-100"
            } flex-grow flex justify-center items-center rounded-lg shadow-md p-4 h-12`} 
            onClick={() => handleButton("책임")}
          >
            <p className="font-medium">책임</p>
          </div>
          <div
            className={`${
              selectedButton === "비"
                ? "bg-main-color text-gray-0"
                : "border-gray-100"
            } flex-grow flex justify-center items-center rounded-lg shadow-md p-4 h-12`}
            onClick={() => handleButton("비")}
          >
            <p className="font-medium">비용</p>
          </div>
          <div
            className={`${
              selectedButton === "염증"
                ? "bg-main-color text-gray-0"
                : "border-gray-100"
            } flex-grow flex justify-center items-center rounded-lg shadow-md p-4 h-12`}
            onClick={() => handleButton("염증")}
          >
            <p className="font-medium">염증</p>
          </div>
        </div>

        {/* 보험 리스트 출력 */}
        <div className="grid gap-4">
          {selectedInsuranceList.map((item, index) => (
            <div key={index} className="border border-gray-100 rounded-lg shadow-md flex justify-between p-5">
              <div 
                className="flex items-center space-x-4 x-full h-10"
                onClick={() => handleCardClick(item.insurance.insuranceId)}>
                <img
                  src={item.insurance.s3ImageUrl}
                  alt=""
                  className="w-[50px] h-[50px]"
                />
                <div className="space-x-1">
                  <p className="font-semibold">{item.insurance.name}</p>
                  <p className="text-gray-500 text-sm">{`${item.insurance.fee}`}</p>
                </div>
              </div>
            </div>
          ))}
        </div>

        {/* 보험 상세 정보 모달 */}
        {isModalOpen && (
          <InsuranceDetailModal
            insuranceDetail={selectedInsurance!}
            onClose={closeModal}
          />
        )}
      </div>
    </div>
  );
};
