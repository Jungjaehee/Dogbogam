import { TopBar } from "../../components/Topbar";
import { Button } from "../../components/Button";
import useUserStore from "../../store/UseUserStore";
import { getBreedResult } from "../../api/breedAPI";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { AiDiagnosis } from "../../models/record.model";
import { Breed } from "./components/breed";

export const BreedResult = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id } = location.state; // 'id'가 제대로 전달되었는지 확인 필요
  const { dogInfo } = useUserStore();

  // 로그 추가: id가 정상적으로 들어오는지 확인
  console.log("Location state id:", id);

  const [result, setResult] = useState<AiDiagnosis>({
    aiDiagnosisId: id,
    dogId: dogInfo?.dogId || 0,
    createdAt: new Date(),
    imageUrl: "",
    normal: true,
    diagnosisItem: "",
    diseases: [],
  });

  const confirm = () => {
    navigate("/home");
  };

  const getResult = async () => {
    try {
      // 로그 추가: API 요청이 시작되었는지 확인
      console.log("API 호출 시작, id:", id);
      const response: AiDiagnosis = await getBreedResult(id);

      // 로그 추가: API 응답이 제대로 왔는지 확인
      console.log("API 응답:", response);

      setResult(response); // setResult로 값을 업데이트
    } catch (error) {
      console.error("예측 결과 가져오기 실패", error);
    }
  };

  useEffect(() => {
    if (id) {
      // 로그 추가: useEffect가 실행되는지 확인
      console.log("useEffect 실행됨, id:", id);
      getResult(); // id가 존재하는 경우에만 결과 조회
    }
  }, [id]);

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col justify-between overflow-y-auto">
      <div>
        <TopBar pre={""} title={""} skip={""} />
        <div className="space-y-3 mb-6">
          <p className="text-xl font-semibold">
            <span className="text-main-color">{dogInfo.name}</span>의{" "}
            
            <br />
            AI 견종 예측 리포트
          </p>
          <p className="text-gray-400 font-medium text-sm">
            재미로 보는 우리 아이 견종이에요
          </p>
        </div>
        <div className="space-y-10 mb-10">
          <Breed result={result} />
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
