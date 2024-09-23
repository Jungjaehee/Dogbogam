import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export const Start = () => {
  const navigate = useNavigate();
  useEffect(() => {
    setTimeout(() => {
      navigate("/start", { replace: true });
    }, 2000);
  }, []);

  return (
    <div className="h-full flex flex-col justify-center pt-6 px-4 text-center bg-white">
      <div className="space-y-3 mb-16">
        <p className="text-2xl font-semibold">회원가입 완료!</p>
        <p className="font-medium">멍이냥의 회원이 되신 것을 환영해요</p>
      </div>
    </div>
  );
};
