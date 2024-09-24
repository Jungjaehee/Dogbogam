import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/BackButton.png";
import MedicalRecordList from "./components/MedicalRecordList";
import RegistIcon from "../../assets/RegistIcon.png"; 

const dummyData = [
  {
    medicalRecordId: 1,
    dogId: 101,
    date: "2023-09-10",
    content: "피부 질환 치료",
    hospital: "서울 동물 병원",
    imageName: null,
    imageUrl: null,
    createdAt: new Date("2023-09-10T14:00:00Z"),
    modifiedAt: new Date("2023-09-12T10:00:00Z"),
  },
  {
    medicalRecordId: 2,
    dogId: 102,
    date: "2023-08-25",
    content: "안구 건조증 치료",
    hospital: "서울 중앙 동물 병원",
    imageName: null,
    imageUrl: null,
    createdAt: new Date("2023-08-25T10:30:00Z"),
    modifiedAt: new Date("2023-08-26T09:00:00Z"),
  },
  {
    medicalRecordId: 3,
    dogId: 103,
    date: "2023-07-15",
    content: "관절 치료",
    hospital: "강남 동물 병원",
    imageName: null,
    imageUrl: null,
    createdAt: new Date("2023-07-15T09:00:00Z"),
    modifiedAt: new Date("2023-07-16T11:00:00Z"),
  },
];

const MedicalRecord = () => {
  const navigate = useNavigate();

  const ClickBackButton = () => {
    navigate(-1);
  };

  const ClickRegistButton = () => {
    navigate("/regist-record"); 
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
      <h1 className="text-xl text-gray-700 font-semibold mb-2">
        의료 기록 관리
      </h1>
      <p className="text-gray-500 text-sm mb-2.5">
        반려견의 진료 기록을 확인하고 등록하세요.
      </p>

      {/* 진료 기록 리스트 */}
      <MedicalRecordList records={dummyData} />

      {/* 진료 기록 등록 버튼 */}
      <div className="flex justify-center mt-6">
        <button
          onClick={ClickRegistButton}
          className="flex items-center space-x-2"
        >
          <img
            src={RegistIcon}
            alt="Add Medical Record Icon"
            className="w-5 h-5"
          />
          <span className="text-gray-500 font-semibold text-sm">
            진료 기록 등록
          </span>
        </button>
      </div>
    </div>
  );
};

export default MedicalRecord;
