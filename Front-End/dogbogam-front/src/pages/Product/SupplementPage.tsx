import { TopBar } from "../../components/Topbar/index";
import { useEffect, useState } from "react";
import useUserStore from "../../store/UseUserStore";
import { Supplements } from "../../api/supplementAPI";
import { getSupplementList } from "../../api/supplementAPI";
import { getSupplement } from "../../api/supplementAPI";
import SupplementDetailModal from "./components/SupplementDetailModal";
import { supplement } from "../../api/supplementAPI";

export const SupplementPage = () => {
  const { dogInfo } = useUserStore();

  const [supplementList, setSupplementList] = useState<Supplements[]>([]);
  const [selectedSupplement, setSelectedSupplement] = useState<supplement | null>(null);
  const [size] = useState(4); 
  const [page, setPage] = useState(0); 
  const [isModalOpen, setIsModalOpen] = useState(false);

  const fetchSupplementList = async (size: number, page: number) => {
    try {
      const data = await getSupplementList(size, page);
      console.log("supplementList", data);
      setSupplementList(data); 
    } catch (error) {
      console.log("영양제 리스트를 가져오는 중 오류 발생", error);
    }
  };

  const fetchSupplement = async (supplementId: number) => {
    try {
      const data = await getSupplement(supplementId);
      setSelectedSupplement(data);
      return data;
    } catch (error) {
      console.log("영양제 가져오기 실패", error);
      return null;
    }
  }

  useEffect(() => {
    fetchSupplementList(size, page);
  }, [size, page]);

  const handleNextPage = () => {
    setPage(prevPage => prevPage + 1);
  };

  const handlePrevPage = () => {
    if (page > 0) {
      setPage(prevPage => prevPage - 1);
    }
  };

  const handleCardClick = async (supplementId: number) => {
    const supplementDetail = await fetchSupplement(supplementId);
    if (supplementDetail) {
      setIsModalOpen(true);
    }
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedSupplement(null);
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <TopBar pre={"/home"} title={""} skip={""} />
        <div className="mb-6">
          <p className="text-gray-800 text-xl font-bold">
            <span className="text-main-color">{`${dogInfo.name}`}</span>
            를 위한, <br /> 영양제
          </p>
        </div>
        
      <div className="grid grid-cols-2 gap-4 mb-6">
        {supplementList.map((supplement) => (
          <div
            key={supplement.supplementId} 
            className="border border-gray-200 rounded-lg shadow-lg p-5"
          >
            <div 
              className="flex flex-col items-start space-y-4 h-50"
              onClick={() => handleCardClick(supplement.supplementId)}
            >
              <img
                src={supplement.imageUrl}
                alt=""
                className="w-full h-[80px]"
              />
              <div className="flex flex-col items-start">
                <p className="font-semibold mb-2">{supplement.productName}</p>
                <p className="text-gray-500 text-sm">{`${supplement.price}원`}</p>
              </div>
            </div>
          </div>
        ))}
      </div>

        {/* 페이지네이션 버튼 */}
        <div className="flex justify-between">
          <button
            onClick={handlePrevPage}
            disabled={page === 0}
            className={`p-2 rounded-md ${page === 0 ? "bg-gray-200" : "bg-main-color"}`}
          >
            이전
          </button>
          <button onClick={handleNextPage} className="p-2 bg-main-color rounded-md">
            다음
          </button>
        </div>

        {isModalOpen && (
          <SupplementDetailModal
            supplementDetail={selectedSupplement}
            onClose={closeModal}
          />
        )}

      </div>
    </div>
  );
};
