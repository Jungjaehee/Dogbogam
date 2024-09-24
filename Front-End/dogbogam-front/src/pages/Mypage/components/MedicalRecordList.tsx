import MedicalRecordItem from "./MedicalRecordItem";
import NonMedicalRecordIcon from "../../../assets/NonMedicalRecordIcon.png";

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
  return (
    <div>
      {records.length > 0 ? (
        // 진료 기록이 있을 때
        records.map((record) => (
          <MedicalRecordItem
            key={record.medicalRecordId.toString()}
            record={record}
          />
        ))
      ) : (
        // 진료 기록이 없을 때
        <div className="flex flex-col items-center justify-center h-[calc(100vh-200px)]">
          <img
            src={NonMedicalRecordIcon}
            alt="NonMedicalRecord Icon"
            className="w-18 h-18 mb-4"
          />
          <span className="text-lg font-semibold text-gray-700">
            진료 기록이 없어요.
          </span>
        </div>
      )}
    </div>
  );
};

export default MedicalRecordList;
