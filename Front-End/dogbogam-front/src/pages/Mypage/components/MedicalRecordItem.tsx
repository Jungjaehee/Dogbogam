import { useNavigate } from "react-router-dom";
import { calRelativeTime } from "../../../utils/calcDate";

interface MedicalRecord {
  record: {
    medicalRecordId: number;
    dogId: number;
    date: string;
    content: string | null;
    hospital: string;
    imageName?: string | null;
    imageUrl?: string | null;
    createdAt: Date;
    modifiedAt: Date;
  };
}

const MedicalRecordItem = ({ record }: MedicalRecord) => {
  const navigate = useNavigate();

  const ClickRecord = () => {
    navigate(`${record.medicalRecordId}`);
  };

  const recordDate = new Date(record.date);
  const relativeTime = calRelativeTime(recordDate);

  return (
    <div
      className="p-4 bg-white rounded-lg shadow-md mb-4 cursor-pointer"
      onClick={ClickRecord}
    >
      <div className="flex justify-between items-center">
        
        {/* 병원 이름과 진료내용 */}
        <div className="flex flex-col">
          <span className="text-gray-700 font-bold">{record.hospital}</span>
          <span className="text-gray-500 text-sm">{record.content}</span>
        </div>

        {/* 진료 시간 */}
        <span className="text-gray-500 text-sm">{relativeTime}</span>
      </div>
    </div>
  );
};

export default MedicalRecordItem;
