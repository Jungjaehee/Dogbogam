import { useState } from "react";
import { TopBar } from "../../components/Topbar";
import { useNavigate } from "react-router-dom";
// import useUserStore from "../../store/UseUserStore";
import { Button } from "../../components/Button";
import eye from "../../assets/Signup/health/eye.png";
import skin from "../../assets/Signup/health/skin.png";
import obesity from "../../assets/Signup/health/fat.png";

export const SelectPart = () => {
  const navigate = useNavigate();
  // const setToken = useUserStore((state) => state.setToken);
  const [selectedProblem, setSelectedProblem] = useState<string>(""); // 선택된 문제를 문자열로 관리
  const problemName = [
    { image: eye, name: "눈" },
    { image: skin, name: "피부" },
    { image: obesity, name: "비만" },
  ];
  const rows = Math.ceil(problemName.length / 2); // 행의 수 계산

  // 문제를 추가하거나 삭제하는 함수
  const toggleProblem = (problem: { image: string; name: string }) => {
    setSelectedProblem((prevState) => {
      if (prevState === problem.name) {
        return "";
      } else {
        return problem.name;
      }
    });
  };

  const next = async () => {
    navigate("/AI/photo", { state: { selectedProblem } });
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div className="h-full flex flex-col">
        <TopBar pre={"/home"} title={""} skip={""} />
        <div className="space-y-3">
          <p className="text-xl font-semibold">
            진단을 원하는 <span className="text-main-color">신체 부위</span>를{" "}
            <br />
            선택해주세요
          </p>
          <p className="text-gray-400 font-medium text-sm">
            하나만 선택이 가능해요
          </p>
        </div>
        <div className="space-y-6 flex flex-col flex-grow justify-center place-items-center place-content-center">
          {Array.from({ length: rows }).map((_, rowIndex) => (
            <div key={rowIndex} className="flex space-x-6">
              {problemName
                .slice(rowIndex * 2, rowIndex * 2 + 2)
                .map((problem) => {
                  const isSelected = selectedProblem === problem.name; // 선택 여부 확인
                  return (
                    <div
                      key={problem.name}
                      onClick={() => toggleProblem(problem)} // 클릭 시 문제 토글
                      className={`w-[90px] h-[90px] flex flex-col place-items-center place-content-center border shadow-lg ${
                        isSelected
                          ? "border-main-color bg-[#FFE6A7]"
                          : "border-gray-500"
                      } rounded-lg space-y-3 text-center cursor-pointer`}
                    >
                      <img src={problem.image} alt="" className="w-9 h-9" />
                      <p>{problem.name}</p>
                    </div>
                  );
                })}
            </div>
          ))}
        </div>
      </div>
      <Button onClick={() => next()} text={"확인"} bgColor={"bg-main-color"} />
    </div>
  );
};
