import { useNavigate } from "react-router-dom";
import { calRelativeTime } from "../../../utils/calcDate";
import { MedicalRecord, VaccinationRecord } from "../../../models/record.model";
import { checkRound } from "../../../utils/calcStatus";
// 타입 가드 함수 - record가 VaccinationRecord인지 확인
const checkRecord = (
  record: MedicalRecord | VaccinationRecord
): record is VaccinationRecord => {
  return (record as VaccinationRecord).vaccinationRound !== undefined;
};

interface RecordItem {
  record: MedicalRecord | VaccinationRecord;
}

const MedicalRecordItem = ({ record }: RecordItem) => {
  const navigate = useNavigate();

  const ClickRecord = () => {
    if (checkRecord(record)) {
      // 예방 접종 기록일 경우 id를 state로 넘겨줌
      navigate(`/vaccination/${record.vaccinationRecordId}`, {
        state: { id: record.vaccinationRecordId },
      });
    } else {
      // 병원 진료 기록일 경우 id를 state로 넘겨줌
      navigate(`/medical/${record.medicalRecordId}`, {
        state: { id: record.medicalRecordId },
      });
    }
  };

  const recordDate = new Date(record.recordTime);
  const relativeTime = calRelativeTime(recordDate);

  return (
    <div
      className="p-4 bg-white rounded-lg shadow-md mb-4 py-6 cursor-pointer"
      onClick={ClickRecord}
    >
      <div className="flex justify-between items-center">
        {/* 병원 이름과 진료 내용 또는 접종 내용 */}
        <div className="flex flex-col">
          <span className="text-gray-700 font-bold">{record.hospital}</span>

          {/* VaccinationRecord일 때 예방 접종 차수 출력 */}
          {checkRecord(record) ? (
            <span className="text-gray-500 text-sm">
              {checkRound(record.vaccinationRound)}
            </span>
          ) : (
            <span className="text-gray-500 text-sm">{record.content}</span>
          )}
        </div>

        {/* 진료/접종 시간 */}
        <span className="text-gray-500 text-sm">{relativeTime}</span>
      </div>
    </div>
  );
};

export default MedicalRecordItem;
