import { useState, useRef } from "react";
import addPhotoIcon from "../../../assets/MyPage/addPhotoIcon.png";
import { registVaccination } from "../../../api/vaccinationRecordAPI";
import useUserStore from "../../../store/UseUserStore";

const VaccinationRecordForm = ({ handleBack }: { handleBack: () => void }) => {
  const { dogInfo } = useUserStore();
  const fileInputRef = useRef<HTMLInputElement>(null);
  const [selectedFile, setSelectedFile] = useState<File | null>(null);

  const [date, setDate] = useState("");
  const [time, setTime] = useState("");
  const [vaccinationRecord, setVaccinationRecord] = useState({
    dogId: dogInfo.dogId,
    recordTime: new Date(),
    hospital: "",
    vaccinationRound: 1,
    content: "",
    cost: 0,
  });

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      setSelectedFile(file);
    }
  };

  const handleFileButtonClick = () => {
    fileInputRef.current?.click();
  };

  const combineDateTime = (date: string, time: string): Date => {
    return new Date(`${date}T${time}`);
  };

  const handleSubmit = async () => {
    const combinedDateTime = combineDateTime(date, time);
    const updatedVaccinationRecord = {
      ...vaccinationRecord,
      recordTime: combinedDateTime,
      image: selectedFile,
    };
    try {
      const response = await registVaccination(updatedVaccinationRecord);
      alert("예방 접종 기록이 성공적으로 등록되었습니다.");
      handleBack();
    } catch (error) {
      console.error("예방 접종 기록 등록 실패:", error);
      alert("예방 접종 기록 등록 중 오류가 발생했습니다.");
    }
  };

  return (
    <div>
      {/* 병원 입력 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          병원<span className="text-main-color ml-0.5">*</span>
        </label>
        <input
          type="text"
          className="w-full py-2 px-3 border border-gray-100 rounded-md text-gray-700 placeholder:text-sm"
          placeholder="병원을 입력해주세요"
          required
          value={vaccinationRecord.hospital}
          onChange={(e) =>
            setVaccinationRecord((prevRecord) => ({
              ...prevRecord,
              hospital: e.target.value,
            }))
          }
        />
      </div>

      {/* 예방 접종 차수 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          예방 접종 차수<span className="text-main-color ml-0.5">*</span>
        </label>
        <select
          className="w-full py-2 px-3 border border-gray-100 rounded-md text-gray-700 text-sm"
          value={vaccinationRecord.vaccinationRound}
          onChange={(e) =>
            setVaccinationRecord((prevRecord) => ({
              ...prevRecord,
              vaccinationRound: Number(e.target.value),
            }))
          }
        >
          <option value={1}>
            1차 예방 접종 (종합 백신 1차 + 코로나 장염 1차)
          </option>
          <option value={2}>
            2차 예방 접종 (종합 백신 2차 + 코로나 장염 2차)
          </option>
          <option value={3}>
            3차 예방 접종 (종합 백신 3차 + 켄넬코프 1차)
          </option>
          <option value={4}>
            4차 예방 접종 (종합 백신 4차 + 켄넬코프 2차)
          </option>
          <option value={5}>
            5차 예방 접종 (종합 백신 5차 + 인플루엔자 1차)
          </option>
          <option value={6}>기타 접종</option>
        </select>
      </div>

      {/* 날짜와 시간 선택 */}
      <div className="flex flex-row space-x-4">
        <div className="flex-1 mb-4">
          <label className="block text-gray-700 text-sm font-semibold mb-1">
            날짜<span className="text-main-color ml-0.5">*</span>
          </label>
          <input
            type="date"
            className="w-full py-2 px-3 border rounded-md text-gray-700"
            required
            value={date}
            onChange={(e) => setDate(e.target.value)}
          />
        </div>
        <div className="flex-1 mb-4">
          <label className="block text-gray-700 text-sm font-semibold mb-1">
            시간<span className="text-main-color ml-0.5">*</span>
          </label>
          <input
            type="time"
            className="w-full py-2 px-3 border rounded-md text-gray-700"
            required
            value={time}
            onChange={(e) => setTime(e.target.value)}
          />
        </div>
      </div>

      {/* 비용 입력 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          비용<span className="text-main-color ml-0.5">*</span>
        </label>
        <input
          type="text"
          className="w-full py-2 px-3 border rounded-md text-gray-700"
          placeholder="비용을 입력해주세요"
          required
          value={vaccinationRecord.cost.toLocaleString("ko-KR") + " 원"}
          onChange={(e) => {
            const onlyNumbers = e.target.value.replace(/[^\d]/g, "");
            setVaccinationRecord((prevRecord) => ({
              ...prevRecord,
              cost: Number(onlyNumbers),
            }));
          }}
        />
      </div>

      {/* 이미지 업로드 */}
      <div className="mb-4">
        <label className="block text-gray-500 text-xs mb-1">
          검사 결과지 및 메모 추가(선택)
        </label>
        <div className="flex items-center space-x-2">
          <button className="w-7 h-7" onClick={handleFileButtonClick}>
            <img src={addPhotoIcon} alt="Add Icon" className="w-7 h-7" />
            <input
              type="file"
              ref={fileInputRef}
              style={{ display: "none" }}
              onChange={handleFileChange}
            />
          </button>
        </div>
      </div>
      {/* content 입력 추가 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          진료 내용<span className="text-main-color ml-0.5">*</span>
        </label>
        <textarea
          className="w-full py-2 px-3 border border-gray-100 rounded-md text-gray-700 placeholder:text-sm"
          placeholder="진료 내용을 입력해주세요"
          required
          value={vaccinationRecord.content}
          onChange={(e) =>
            setVaccinationRecord((prevRecord) => ({
              ...prevRecord,
              content: e.target.value,
            }))
          }
        />
      </div>

      <button
        className="w-full bg-yellow-400 text-white font-semibold py-3 rounded-lg shadow-md"
        onClick={handleSubmit}
      >
        등록 하기
      </button>
    </div>
  );
};

export default VaccinationRecordForm;
