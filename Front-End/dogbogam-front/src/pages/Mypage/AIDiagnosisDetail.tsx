import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useEffect, useState } from "react";
import { Normal } from "../../pages/AIprediction/components/Normal";
import { Abnormal } from "../../pages/AIprediction/components/Abnormal";
import { RecommendInsurance } from "../../pages/AIprediction/components/RecommendInsurance";
import { RecommendNutrient } from "../../pages/AIprediction/components/RecommendNutrient";
import { AiDiagnosis } from "../../models/record.model";
import { getInsurance, getNutrient } from "../../api/aiPredictionAPI";
import { recommendInsurance } from "../../models/insurance.model";
import { recommendSupplement } from "../../models/supplement.model";
import useUserStore from "../../store/UseUserStore";
import { getDiagnosisDetail } from "../../api/aiDiagnosisAPI";

export const AIDiagnosisDetail = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { id } = location.state;
  const { dogInfo } = useUserStore();
  console.log(dogInfo)

  const [result, setResult] = useState<AiDiagnosis>();
  console.log(result)
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
    const resultResponse = await getDiagnosisDetail(id);
    setResult(resultResponse);
    console.log(resultResponse);
    const insuranceResponse = await getInsurance(resultResponse.diagnosisItem);
    setInsurance(insuranceResponse);
    console.log(resultResponse);
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
            {/* <span className="text-main-color">{result.diagnosisItem}</span> */}
            <br />
            AI 질병 예측 리포트
          </p>
          <p className="text-gray-400 font-medium text-sm">
            {/* {result.createdAt.toLocaleDateString()} */}
          </p>
        </div>
        <div className="space-y-10 mb-10">
          {/* {result.normal ? ( */}
            {/* <Normal result={result} /> */}
          {/* ) : ( */}
            {/* <Abnormal result={result} /> */}
          {/* )} */}
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

export default AIDiagnosisDetail;