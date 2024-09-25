import MedicalRecordItem from "./MedicalRecordItem";
import NonMedicalRecordIcon from "../../../assets/MyPage/NonMedicalRecordIcon.png";
import RegistIcon from "../../../assets/MyPage/RegistIcon.png";
import { useNavigate } from "react-router-dom";

interface MedicalRecordList {
  records: {
    medicalRecordId: number;
    dogId: number;
    date: string;
    content: string | null;
    hospital: string;
    imageName?: string | null;
    imageUrl?: string | null;
    createdAt: Date;
    modifiedAt: Date;
  }[];
}

const MedicalRecordList = ({ records }: MedicalRecordList) => {
  const navigate = useNavigate();

  const ClickRegistButton = () => {
    navigate("/mypage/regist-record");
  };

  return (
    <div>
      {records.length > 0 ? (
        // 진료 기록이 있을 때
        <>
          {records.map((record) => (
            <MedicalRecordItem
              key={record.medicalRecordId.toString()}
              record={record}
            />
          ))}
          {/* 진료 기록 등록 버튼 */}
          <div className="flex justify-center mt-6">
            <button
              onClick={ClickRegistButton}
              className="flex items-center space-x-2"
            >
              <img
                src={RegistIcon}
                alt="Add Icon"
                className="w-5 h-5"
              />
              <span className="text-gray-500 font-semibold text-sm">
                진료 기록 등록
              </span>
            </button>
          </div>
        </>
      ) : (
        // 진료 기록이 없을 때
        <div className="flex flex-col items-center justify-center h-[calc(100vh-200px)]">
          <img
            src={NonMedicalRecordIcon}
            alt="NonMedicalRecord Icon"
            className="w-18 h-18 mb-4"
          />
          <span className="text-md text-gray-500">진료 기록이 없어요.</span>
          <button
            onClick={ClickRegistButton}
            className="w-3/4 bg-main-color text-gray-0 font-semibold py-2.5 px-10 rounded-lg shadow-md mt-[8vh]"
          >
            진료 기록 등록하기
          </button>
        </div>
      )}
    </div>
  );
};

export default MedicalRecordList;
