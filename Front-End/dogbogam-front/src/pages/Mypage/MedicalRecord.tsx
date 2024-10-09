import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/MyPage/BackButton.png";
import MedicalRecordList from "./components/MedicalRecordList";
import useUserStore from "../../store/UseUserStore";
// import type { MedicalRecord, VaccinationRecord } from "../../models/record.model";
import useMedicalRecordStore from "../../store/UseMedicalRecordStore";
import { getMyMedicalRecord } from "../../api/medicalRecordAPI";
import { getMyVaccination } from "../../api/vaccinationRecordAPI";
import useVaccinationStore from "../../store/useVaccinationStore";

const MedicalRecord = () => {
  const navigate = useNavigate();

  const { dogInfo } = useUserStore();
  const { setMedicalRecordList, medicalRecordList } = useMedicalRecordStore();
  const { setVaccinationList, vaccinationList } = useVaccinationStore();
  
  const getMedicalRecord = async () => {
    const medicalResponse = await getMyMedicalRecord(dogInfo.dogId);
    setMedicalRecordList(medicalResponse.data);
  };

  const getVaccination = async () => {
    const vaccinationResponse = await getMyVaccination(dogInfo.dogId);
    setVaccinationList(vaccinationResponse.data);
  };

  // 병원 진료와 예방 접종 기록을 병합하여 정렬된 배열 생성
  const records = [...medicalRecordList, ...vaccinationList].sort(
    (a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
  );

  useEffect(() => {
    const fetchData = async () => {
      await getMedicalRecord();
      await getVaccination();
    };

    fetchData();
  }, []); // 최초 1회 렌더링 될 때 데이터 호출

  const ClickBackButton = () => {
    navigate(-1);
  };

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0 overflow-y-scroll">
      {/* 뒤로가기 버튼 */}
      <button>
        <img
          src={BackButton}
          alt="Back Button"
          className="w-7 h-7 mb-2.5"
          onClick={ClickBackButton}
        />
      </button>

      {/* 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">진료 기록</h1>
      <p className="text-gray-500 text-sm mb-2.5">
        반려견의 진료 기록을 확인하고 등록하세요.
      </p>

      {/* 병합된 진료 기록 리스트 */}
      <MedicalRecordList records={records} />
    </div>
  );
};

export default MedicalRecord;
