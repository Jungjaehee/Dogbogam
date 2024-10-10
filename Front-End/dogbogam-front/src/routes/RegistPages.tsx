import { Route, Routes } from "react-router-dom";
import RegistDog from "../pages/Mypage/RegistDog";
import RegistDogHealth from "../pages/Mypage/RegistDogHealth";

const RegistPages = () => {
  return (
    <div className="h-[100vh] relative">
      <Routes>
        {/* 최초 정보 수정 페이지 */}
        <Route path="/" element={<RegistDog />} />

        {/* 세부 페이지 경로 */}
        <Route path="health" element={<RegistDogHealth />} />
      </Routes>
    </div>
  );
};

export default RegistPages;
