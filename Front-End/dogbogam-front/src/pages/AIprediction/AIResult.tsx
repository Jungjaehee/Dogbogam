import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useState } from "react";
import { Normal } from "./components/Normal";
import { Abnormal } from "./components/Abnormal";
import { RecommendInsurance } from "./components/RecommendInsurance";
import { RecommendNutrient } from "./components/RecommendNutrient";

interface Disease {
  name: string;
  percentage: string;
}

interface Result {
  reportId: number;
  dogId: number;
  diagnosisDate: string;
  imageName: string;
  imageUrl: string;
  normal: boolean;
  diagnosisItem: string;
  diseases: Disease[];
}

export const AIResult = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { Id } = location.state;
  const [result /* setResult */] = useState<Result>({
    reportId: Id,
    dogId: 0,
    diagnosisDate: "",
    imageName: "",
    imageUrl: "",
    normal: true,
    diagnosisItem: "",
    diseases: [],
  });
  const confirm = () => {
    navigate("/home");
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between overflow-y-auto">
      <div className="">
        <TopBar pre={""} title={""} skip={""} />
        <div className="space-y-3 mb-10">
          <p className="text-xl font-semibold">
            <span className="text-main-color">새우</span>의{" "}
            <span className="text-main-color">눈</span>
            <br />
            AI 질병 예측 리포트
          </p>
          <p className="text-gray-400 font-medium text-sm">
            데이터는 마이페이지에서 계속 볼 수 있어요
          </p>
        </div>
        <div className="space-y-10 mb-10">
          {result.normal ? (
            <Normal result={result} />
          ) : (
            <Abnormal result={result} />
          )}
          <RecommendNutrient />
          <RecommendInsurance />
        </div>
      </div>
      <Button
        text={"확인 완료"}
        onClick={() => {
          confirm();
        }}
        bgColor={"bg-main-color"}
      />
    </div>
  );
};
