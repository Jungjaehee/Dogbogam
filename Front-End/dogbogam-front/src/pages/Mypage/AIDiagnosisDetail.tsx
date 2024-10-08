import { useLocation} from "react-router-dom";
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
import { getAiDiagnosisDay } from "../../utils/calcDate";

export const AIDiagnosisDetail = () => {
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

   const getResult = async () => {
     const resultResponse = await getDiagnosisDetail(id);
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

const formattedDate = getAiDiagnosisDay(result.createdAt)

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between overflow-y-auto">
      <div className="">
        <TopBar pre={""} title={""} skip={""} />
        <div className="space-y-1 mb-1">
          <p className="text-xl font-semibold">
            <span className="text-main-color">{dogInfo.name}</span>의{" "}
            <span className="text-main-color">{result.diagnosisItem}</span>
            <br />
            AI 질병 예측 리포트
          </p>
          <p className="text-gray-400 font-medium text-sm">{formattedDate}요일에 실시한 검사 결과에요</p>
        </div>
        <div className="space-y-8 mb-1">
          {result.normal ? (
            <Normal result={result} />
          ) : (
            <Abnormal result={result} />
          )}
          <RecommendNutrient nutrient={nutrient} />
          <RecommendInsurance insurance={insurance} />
        </div>
      </div>
    </div>
  );
};

export default AIDiagnosisDetail;