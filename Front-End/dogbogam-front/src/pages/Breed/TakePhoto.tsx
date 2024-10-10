import React from "react";
import Webcam from "react-webcam";
import { TopBar } from "../../components/Topbar";
import { useNavigate } from "react-router-dom";
import frame from "../../assets/AIprediction/frame.png";
import useUserStore from "../../store/UseUserStore";

export const TakePhoto = () => {
  const navigate = useNavigate();

  const next = async (image: File) => {
    navigate("/breed/check", {
      state: { image: image },
    });
  };

  const { dogInfo } = useUserStore();

  const handleCapture = async () => {
    const screenshot = webcamRef.current?.getScreenshot();
    if (screenshot) {
      // Base64 데이터를 Blob으로 변환
      const response = await fetch(screenshot);
      const blob = await response.blob();

      const file = new File([blob], "photo.jpg", { type: "image/jpeg" });
      console.log("사진 찍기 완료", file);
      next(file);
    }
  };

  const FACING_MODE_ENVIRONMENT_ = "environment";
  const videoConstraints = {
    facingMode: FACING_MODE_ENVIRONMENT_,
  };

  const webcamRef = React.useRef<Webcam>(null); // 웹캠 참조 추가

  return (
    <div className="h-full pt-6 px-4 bg-gray-500 flex flex-col relative">
      <Webcam
        id="main-webcam"
        ref={webcamRef} // 웹캠 참조 설정
        style={{
          width: "100%",
          height: "100%",
          objectFit: "cover",
          position: "absolute",
          top: 0,
          left: 0,
        }}
        className="z-0"
        audio={false}
        screenshotFormat="image/jpeg"
        videoConstraints={videoConstraints}
      />
      <TopBar pre={""} title={""} skip={""} />
      <div className="flex-grow flex flex-col justify-center place-items-center space-y-16 relative z-10">
        <div className="flex flex-col space-y-3 place-items-center">
          <p className="text-xl font-semibold text-white">
            <span className="text-main-color">{dogInfo.name}</span>을
            촬영해주세요
          </p>
          <p className="text-white font-medium text-sm">
            가이드 라인에 맞춰 촬영해 주세요
          </p>
        </div>

        <img src={frame} alt="" className="w-4/5" />
        <div
          onClick={handleCapture} // 클릭 시 사진 촬영
          className="w-[70px] h-[70px] rounded-full bg-main-color flex place-content-center place-items-center shadow-lg cursor-pointer"
        >
          <div className="w-[60px] h-[60px] rounded-full border-2 border-white"></div>
        </div>
      </div>
    </div>
  );
};
