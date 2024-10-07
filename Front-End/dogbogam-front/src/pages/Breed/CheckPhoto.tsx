import { useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useEffect, useState } from "react";
import { Loading } from "./Loading";

export const CheckPhoto = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false); // 로딩 상태 관리
  const handleCheck = () => {
    setLoading(true); // 로딩 시작
  };

  useEffect(() => {
    if (loading) {
      const timer = setTimeout(() => {
        setLoading(false); // 로딩 종료
        navigate("/breed/result", { state: 1 }); // 로딩이 끝난 후 네비게이트
      }, 10000); // 10000ms = 10초

      return () => clearTimeout(timer); // 컴포넌트 언마운트 시 타이머 정리
    }
  }, [loading, navigate]);

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
            src=""
            alt=""
            className="bg-[#F4F4F4] rounded-lg place-contents-center w-[300px] h-[300px]"
          />
        </div>
      </div>
      <Button
        text={"검사하기"}
        onClick={() => {
          handleCheck();
        }}
        bgColor={"bg-main-color"}
      />
    </div>
  );
};
