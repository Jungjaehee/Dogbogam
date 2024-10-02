import { useState } from "react";
import { TopBar } from "../../components/Topbar";
import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import eye from "../../assets/Signup/health/eye.png";
import bone from "../../assets/Signup/health/bone.png";
import kidney from "../../assets/Signup/health/kidney.png";
import skin from "../../assets/Signup/health/skin.png";
import tooth from "../../assets/Signup/health/tooth.png";
import fat from "../../assets/Signup/health/fat.png";
import heart from "../../assets/Signup/health/heart.png";
import poo from "../../assets/Signup/health/poo.png";
import ear from "../../assets/Signup/health/ear.png";
import { userSignup } from "../../api/userAPI";
import { registDogHealth, registDogInfo } from "../../api/dogAPI";

export const DogHealth = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { inputDogInfo, inputUserInfo } = location.state;
  const [problemList, setProblemList] = useState<string[]>([]);
  const problemName = [
    { image: eye, name: "눈" },
    { image: bone, name: "관절" },
    { image: kidney, name: "면역력" },
    { image: skin, name: "피모" },
    { image: tooth, name: "구강" },
    { image: fat, name: "비만" },
    { image: heart, name: "심장" },
    { image: poo, name: "변비" },
    { image: ear, name: "스트레스" },
  ];
  const rows = Math.ceil(problemName.length / 3); // 행의 수 계산

  // 문제를 추가하거나 삭제하는 함수
  const toggleProblem = (problem: { image: string; name: string }) => {
    setProblemList((prevState) => {
      const isProblemSelected = prevState.some(
        (p) => p === problem.name // 문제 이름으로 비교
      );

      if (isProblemSelected) {
        return prevState.filter((p) => p !== problem.name);
      } else {
        if (prevState.length < 3) {
          return [...prevState, problem.name]; // 문제 추가
        } else {
          alert("문제는 최대 3개까지만 추가할 수 있습니다.");
          return prevState;
        }
      }
    });
  };

  const signup = async () => {
    try {
      const userResponse = await userSignup(inputUserInfo);
      const token = userResponse.accessToken;
      const dogResponse = await registDogInfo(token, inputDogInfo);
      await registDogHealth(token, dogResponse.dogId, problemList);

      navigate("/signup/success", { replace: true });
    } catch (error) {
      console.log("회원 가입 실패: ", error);
    }
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between">
      <div>
        <TopBar pre={"/start"} title={""} skip={"success"} />
        <div className="space-y-3 mb-16">
          <p className="text-xl font-semibold">
            우리 아이의 <br />
            건강고민을 알려주세요
          </p>
          <p className="text-gray-400 font-medium text-sm">
            최대 3개까지 선택 가능해요
          </p>
        </div>
        <div className="space-y-5">
          {Array.from({ length: rows }).map((_, rowIndex) => (
            <div key={rowIndex} className="flex justify-between">
              {problemName
                .slice(rowIndex * 3, rowIndex * 3 + 3)
                .map((problem) => {
                  const isSelected = problemList.some(
                    (p) => p === problem.name
                  ); // 선택 여부 확인
                  return (
                    <div
                      key={problem.name}
                      onClick={() => toggleProblem(problem)} // 클릭 시 문제 토글
                      className={`w-[90px] h-[90px] flex flex-col place-items-center place-content-center border ${
                        isSelected
                          ? "border-main-color bg-[#FFE6A7]"
                          : "border-gray-500"
                      } rounded-lg space-y-3 text-center cursor-pointer`} // 조건에 따라 className 변경
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
      <Button
        onClick={() => signup()}
        text={"회원가입"}
        bgColor={"bg-main-color"}
      />
    </div>
  );
};
