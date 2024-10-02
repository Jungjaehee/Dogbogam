import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { DogCard } from "./components/dogCard";
import DogSelectModal from "./components/dogSelectModal";
import FindDog from "../../assets/images/findDog.png";
import Insurance from "../../assets/images/insurance.png";
import Supplement from "../../assets/images/supplement.png";
import Hospital from "../../assets/images/hospital.png";
import Left from "../../assets/icons/left.png";
import Right from "../../assets/icons/right.png";
import Down from "../../assets/icons/down.png";
import useUserStore from "../../store/UseUserStore";
import { getDogInfo, getDogList } from "../../api/dogAPI";

export const Home = () => {
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const { token, setDogList, setDogInfo, dogInfo } = useUserStore();

  const handleModalClose = () => {
    setIsModalOpen(false);
  };

  const getInfo = async () => {
    const listResponse = await getDogList(token);
    setDogList(listResponse);
    const dogResponse = await getDogInfo(token, listResponse[0].dogId);
    setDogInfo(dogResponse);
  };

  useEffect(() => {
    const fetchData = async () => {
      console.log(dogInfo.dogId);
      await getInfo(); // await을 붙여 비동기 작업의 완료를 기다림
    };

    fetchData(); // 비동기 함수 호출
  }, [token]);

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <div className="w-32 h-14 flex items-center space-x-2 bg-white rounded-lg shadow-md mt-3 mb-7">
          <DogCard dog={dogInfo} />
          <img
            src={Down}
            className="w-4 h-4 cursor-pointer"
            style={{ cursor: "pointer" }}
            onClick={() => setIsModalOpen(true)} // 클릭 시 모달 열기
          />
        </div>

        {isModalOpen && <DogSelectModal onClose={handleModalClose} />}

        <div className="mb-7">
          <p className="text-gray-800 text-xl font-extrabold">
            오늘의 <span className="text-main-color">{`${dogInfo.name}`}</span>
            는,
            <br />
            활기찬 산책을 하고 왔나요?
          </p>
        </div>

        <div
          className="mb-7 p-4 w-50 h-35 bg-gray-100 rounded-lg flex items-center justify-between shadow-lg"
          onClick={() => {
            navigate("/home/breed");
          }}
          style={{ cursor: "pointer" }}
        >
          <p className="text-gray-700 text-lg font-semibold">
            재미로 보는, <br /> 우리 아이{" "}
            <span className="text-main-color">견종 찾기</span>
          </p>
          <img src={FindDog} className="w-25 h-20" />
        </div>

        <div className="mb-7 w-full h-auto flex space-x-4">
          <div
            className="p-5 w-1/2 h-40 bg-main-color rounded-lg shadow-lg flex flex-col justify-between"
            onClick={() => {
              navigate("/home/insurance");
            }}
            style={{ cursor: "pointer" }}
          >
            <p className="self-start text-gray-0 text-lg font-semibold">
              보험 추천
            </p>
            <img src={Insurance} alt="보험" className="self-end w-20 h-20" />
          </div>

          <div
            className="p-5 w-1/2 h-40 bg-[#56ae75] rounded-lg shadow-lg flex flex-col justify-between"
            onClick={() => {
              navigate("/home/supplement");
            }}
            style={{ cursor: "pointer" }}
          >
            <p className="self-start text-gray-0 text-lg font-semibold">
              영양제 추천
            </p>
            <img src={Supplement} alt="영양제" className="self-end w-20 h-20" />
          </div>
        </div>

        <div
          className="mb-7 p-5 w-50 h-35 bg-gray-400 rounded-lg shadow-md flex items-center justify-between"
          onClick={() => {
            navigate("/home/map");
          }}
          style={{ cursor: "pointer" }}
        >
          <img src={Left} className="w-3 h-4" />
          <img src={Hospital} className="w-25 h-20" />
          <p className="text-gray-0 text-lg font-semibold">
            집주변 동물병원을 <br /> 손 쉽게 찾아봐요!
          </p>
          <img src={Right} className="w-3 h-4" />
        </div>
      </div>
    </div>
  );
};
