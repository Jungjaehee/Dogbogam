import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useEffect, useState } from "react";
import { Loading } from "./Loading";
import {
  eyePrediction,
  obesityPrediction,
  skinPrediction,
} from "../../api/aiPredictionAPI";
import useUserStore from "../../store/UseUserStore";

export const CheckPhoto = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { dogInfo } = useUserStore();
  const { selectedProblem, image } = location.state;

  // id 상태를 정의할 때 useState를 올바르게 사용
  const [id, setId] = useState(0);
  const [loading, setLoading] = useState(false); // 로딩 상태 관리
  const [imageSrc, setImageSrc] = useState<string | null>(null);

  const handleCheck = () => {
    setLoading(true); // 로딩 시작
    startPrediction(); // 예측 시작
  };

  const startPrediction = async () => {
    const data = { dogId: dogInfo.dogId, image };

    let response;
    if (selectedProblem === "눈") {
      response = await eyePrediction(data);
    } else if (selectedProblem === "피부") {
      response = await skinPrediction(data);
    } else {
      response = await obesityPrediction(data);
    }

    setId(response.aiDiagnosisId); // id 설정
  };

  useEffect(() => {
    if (loading) {
      const timer = setTimeout(() => {
        setLoading(false); // 로딩 종료
        navigate("/AI/result", { state: { id } }); // 로딩이 끝난 후 네비게이트
      }, 10000); // 10000ms = 10초

      return () => clearTimeout(timer); // 컴포넌트 언마운트 시 타이머 정리
    }
  }, [loading, navigate, id]); // id를 의존성 배열에 추가

  useEffect(() => {
    // FileReader를 사용하여 이미지 URL 생성
    const reader = new FileReader();
    reader.onloadend = () => {
      setImageSrc(reader.result as string); // 이미지 URL을 상태에 저장
    };
    reader.readAsDataURL(image); // 파일을 Data URL로 읽기
  }, [image]); // image를 의존성 배열에 추가

  if (loading) {
    return <Loading />; // 로딩 컴포넌트 반환
  }

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div>
        <TopBar pre={""} title={""} skip={""} />
        <div className="space-y-3 mb-10">
          <p className="text-xl font-semibold">이 사진으로 진행할까요?</p>
          <p className="text-gray-400 font-medium text-sm">
            다시 찍고 싶다면 뒤로가기를 눌러주세요
          </p>
        </div>
        <div className="flex flex-col place-items-center">
          <img
            src={imageSrc!}
            alt=""
            className="bg-[#F4F4F4] rounded-lg place-contents-center w-[300px] h-[300px]"
          />
        </div>
      </div>
      <Button
        text={"검사하기"}
        onClick={handleCheck}
        bgColor={"bg-main-color"}
      />
    </div>
  );
};
