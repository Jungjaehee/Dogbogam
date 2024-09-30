import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/MyPage/BackButton.png";
import MedicalRecordList from "./components/MedicalRecordList";
import type {
  MedicalRecord,
  VaccinationRecord,
} from "../../models/record.model";

// 병원 진료 더미 데이터
const medicalRecords: MedicalRecord[] = [
  {
    medicalRecordId: 1,
    dogId: 101,
    recordTime: new Date("2024-07-10T14:00:00Z"),
    content: "피부 질환 치료",
    hospital: "서울 동물 병원",
    imageName: null,
    imageUrl: null,
    createdAt: new Date("2023-09-10T14:00:00Z"),
    modifiedAt: new Date("2023-09-12T10:00:00Z"),
    cost: 0,
  },
  {
    medicalRecordId: 3,
    dogId: 103,
    recordTime: new Date("2024-09-24T09:00:00Z"),
    content: "관절 치료",
    hospital: "강남 동물 병원",
    imageName: null,
    imageUrl: null,
    createdAt: new Date("2020-07-15T09:00:00Z"),
    modifiedAt: new Date("2023-07-16T11:00:00Z"),
    cost: 0,
  },
];

// 예방 접종 더미 데이터
const vaccinationRecords: VaccinationRecord[] = [
  {
    vaccinationRecordId: 2,
    dogId: 102,
    recordTime: new Date("2024-06-25T10:30:00Z"),
    content: null,
    hospital: "서울 중앙 동물 병원",
    vaccinationRound: 2,
    imageName: null,
    imageUrl: null,
    createdAt: new Date("2023-08-25T10:30:00Z"),
    modifiedAt: new Date("2023-08-26T09:00:00Z"),
    cost: 0,
  },
  {
    vaccinationRecordId: 4,
    dogId: 101,
    recordTime: new Date("2024-09-20T14:00:00Z"),
    content: null,
    hospital: "서초 동물 병원",
    vaccinationRound: 3,
    imageName: null,
    imageUrl: null,
    createdAt: new Date("2023-06-20T14:00:00Z"),
    modifiedAt: new Date("2023-06-21T10:00:00Z"),
    cost: 0,
  },
];

// 병원 진료와 예방 접종 기록을 병합하여 정렬된 배열 생성
const records = [...medicalRecords, ...vaccinationRecords].sort(
  (a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
);

const MedicalRecord = () => {
  const navigate = useNavigate();

  const ClickBackButton = () => {
    navigate(-1);
  };

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
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
