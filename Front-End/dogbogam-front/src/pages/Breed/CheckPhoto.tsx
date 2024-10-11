import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useEffect, useState } from "react";
import { Loading } from "./Loading";
import useUserStore from "../../store/UseUserStore";
import { breedPrediction } from "../../api/breedAPI";

export const CheckPhoto = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { dogInfo } = useUserStore();
  const { image } = location.state;
  const [id, setId] = useState<number | null>(null); // 초기값을 null로 설정
  const [loading, setLoading] = useState(false); // 로딩 상태 관리
  const [imageSrc, setImageSrc] = useState<string | null>(null);

  const handleCheck = () => {
    setLoading(true);
    startPrediction();
  };

  const startPrediction = async () => {
    const data = { dogId: dogInfo.dogId, image };
    try {
      const response = await breedPrediction(data);
      setId(response.aiDiagnosisId);
    } catch (error) {
      console.error("견종 예측 실패:", error);
      setLoading(false);
    }
  };

  useEffect(() => {
    if (loading && id) {
      const timer = setTimeout(() => {
        setLoading(false); // 로딩 종료
        navigate("/breed/result", { state: { id } }); // id가 있을 때만 네비게이트
      }, 1000); // 1초로 수정

      return () => clearTimeout(timer); // 컴포넌트 언마운트 시 타이머 정리
    }
  }, [loading, id, navigate]);

  useEffect(() => {
    // FileReader를 사용하여 이미지 URL 생성
    const reader = new FileReader();
    reader.onloadend = () => {
      setImageSrc(reader.result as string); // 이미지 URL을 상태에 저장
    };
    reader.readAsDataURL(image); // 파일을 Data URL로 읽기
  }, [image]);

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
            alt="Preview"
            className="bg-[#F4F4F4] rounded-lg place-contents-center w-[300px] h-[300px]"
          />
        </div>
      </div>
      <Button text={"검사하기"} onClick={handleCheck} bgColor={"bg-main-color"} />
    </div>
  );
};
