import { useEffect, useState } from "react";
import { TopBar } from "../../components/Topbar";
import { useLocation, useNavigate } from "react-router-dom";
import frame from "../../assets/AIprediction/frame.png";

export const TakePhoto = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { selectedProblem } = location.state || {}; // 상태에서 선택된 문제 가져오기

  const [finalProblem, setFinalProblem] = useState(selectedProblem); // 상태로 선택된 문제 저장

  useEffect(() => {
    if (finalProblem === "비만") {
      setFinalProblem("전체 모습"); // 상태 업데이트
    }
  }, [finalProblem]); // finalProblem이 변경될 때마다 실행

  const next = async () => {
    navigate("/AI/check" /* { state: { selectedProblem: finalProblem } } */); // 변경된 상태로 이동
  };

  return (
    <div className="h-full pt-6 px-4 bg-gray-500 flex flex-col">
      <TopBar pre={""} title={""} skip={""} />
      <div className="flex-grow flex flex-col justify-center place-items-center space-y-16">
        <div className="flex flex-col space-y-3 place-items-center">
          <p className="text-xl font-semibold text-white">
            <span className="text-main-color">{finalProblem}</span>을
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
