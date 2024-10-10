import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useEffect, useState } from "react";
import { Normal } from "./components/Normal";
import { Abnormal } from "./components/Abnormal";
import { RecommendInsurance } from "./components/RecommendInsurance";
import { RecommendNutrient } from "./components/RecommendNutrient";
import { AiDiagnosis } from "../../models/record.model";
import {
  getAIresult,
  getInsurance,
  getNutrient,
} from "../../api/aiPredictionAPI";
import { recommendInsurance } from "../../models/insurance.model";
import { recommendSupplement } from "../../models/supplement.model";
import useUserStore from "../../store/UseUserStore";

export const AIResult = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { id } = location.state;
  const { dogInfo } = useUserStore();
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
  const [nutrient, setNutrient] = useState<recommendSupplement>({
    supplementId: 0,
    productName: "",
    offer: "",
    price: 0,
    imageUrl: "",
  });

  const confirm = () => {
    navigate("/home");
  };

  const getResult = async () => {
    const resultResponse = await getAIresult(id);
    setResult(resultResponse);
    const insuranceResponse = await getInsurance(resultResponse.diagnosisItem);
    setInsurance(insuranceResponse);
    const problemsArray = dogInfo.healthProblems.map(
      (health) => health.problem
    );
    problemsArray.push(resultResponse.diagnosisItem);
    const resultString = problemsArray.join(", ");
    const nutrientResponse = await getNutrient(resultString);
    setNutrient(nutrientResponse);
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
            <span className="text-main-color">{dogInfo.name}</span>의{" "}
            <span className="text-main-color">{result.diagnosisItem}</span>
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
          <RecommendNutrient nutrient={nutrient} />
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
