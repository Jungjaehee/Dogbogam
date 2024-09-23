import { useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";

export const AIResult = () => {
  const navigate = useNavigate();
  const next = () => {
    navigate("/main");
  };

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
        text={"확인 완료"}
        onClick={() => {
          next();
        }}
        bgColor={"bg-main-color"}
      />
    </div>
  );
};
