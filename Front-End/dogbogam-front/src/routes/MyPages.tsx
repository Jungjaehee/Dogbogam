import {Route , Routes} from "react-router-dom";
import MyPage from "../pages/Mypage/Mypage";
import AIDiagnosis from "../pages/Mypage/AIDiagnosis";
import MedicalRecord from "../pages/Mypage/MedicalRecord";
import MyInsurance from "../pages/Mypage/MyInsurance";
import UpdateInfo from "../pages/Mypage/UpdateInfo";
import RegistMedicalReport from "../pages/Mypage/RegistMedicalReport";
import RegistInsurance from "../pages/Mypage/RegistInsurance";

const MyPages = () => {
    return (
      <div className="w-[360px] h-[780px] relative">
        <Routes>
          {/* 최초 마이 페이지 */}
          <Route path="" element={<MyPage />} />

          {/* 세부 페이지 경로 */}
          <Route path="ai-diagnosis" element={<AIDiagnosis />} />
          <Route path="medical-record" element={<MedicalRecord />} />
          <Route path="my-insurance" element={<MyInsurance />} />
          <Route path="update" element={<UpdateInfo />} />
          <Route path="regist-record" element={<RegistMedicalReport />} />
          <Route path="regist-insurance" element={<RegistInsurance />} />
        </Routes>
      </div>
    );
}

export default MyPages;