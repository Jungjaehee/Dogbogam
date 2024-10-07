import { TopBar } from "../../components/Topbar";
import { useNavigate } from "react-router-dom";
import frame from "../../assets/AIprediction/frame.png";

export const TakePhoto = () => {
  const navigate = useNavigate();

  const next = async () => {
    navigate("/breed/check");
  };

  return (
    <div className="h-full pt-6 px-4 bg-gray-500 flex flex-col">
      <TopBar pre={""} title={""} skip={""} />
      <div className="flex-grow flex flex-col justify-center place-items-center space-y-16">
        <div className="flex flex-col space-y-3 place-items-center">
          <p className="text-xl font-semibold text-white">
            <span className="text-main-color">전체 모습</span>을
            촬영해주세요
          </p>
          <p className="text-white font-medium text-sm">
            가이드 라인에 맞춰 촬영해 주세요
          </p>
        </div>
        <img src={frame} alt="" className="w-4/5" />
        <div
          onClick={() => next()}
          className="w-[70px] h-[70px] rounded-full bg-main-color flex place-content-center place-items-center shadow-lg"
        >
          <div
            onClick={() => next()}
            className="w-[60px] h-[60px] rounded-full border-2 border-white"
          ></div>
        </div>
      </div>
    </div>
  );
};
