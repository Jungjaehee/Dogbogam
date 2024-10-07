import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useEffect, useState } from "react";
import { Normal } from "./components/Normal";
import { Abnormal } from "./components/Abnormal";
import { RecommendInsurance } from "./components/RecommendInsurance";
import { RecommendNutrient } from "./components/RecommendNutrient";
import { AiDiagnosis } from "../../models/record.model";
import { getAIresult, getInsurance } from "../../api/aiPredictionAPI";
import { recommendInsurance } from "../../models/insurance.model";

export const AIResult = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { id } = location.state;
  const [result, setResult] = useState<AiDiagnosis>({
    aiDiagnosisId: id,
    dogId: 0,
    createdAt: new Date(),
    imageUrl: "",
    normal: true,
    diagnosisItem: "",
    diseases: [],
  });
  const [insurance, setInsurance] = useState<recommendInsurance>({
    insuranceId: 0,
    name: "",
    fee: "",
    company: "",
    image: "",
  });

  const confirm = () => {
    navigate("/home");
  };

  const getResult = async () => {
    const resultResponse = await getAIresult(id);
    setResult(resultResponse);
    const insuranceResponse = await getInsurance(resultResponse.diagnosisItem);
    setInsurance(insuranceResponse);
    console.log(insurance);
  };

  useEffect(() => {
    getResult();
  }, []);

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
          <RecommendInsurance insurance={insurance} />
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
