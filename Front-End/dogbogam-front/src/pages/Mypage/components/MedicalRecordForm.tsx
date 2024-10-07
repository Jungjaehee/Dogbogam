import { useState, useRef } from "react";
import addPhotoIcon from "../../../assets/MyPage/addPhotoIcon.png";
import { registMedicalRecord } from "../../../api/medicalRecordAPI";
import useUserStore from "../../../store/UseUserStore";

const MedicalRecordForm = ({ handleBack }: { handleBack: () => void }) => {
  const { dogInfo } = useUserStore();
  const fileInputRef = useRef<HTMLInputElement>(null);
  const [selectedFile, setSelectedFile] = useState<File | null>(null);

  const [date, setDate] = useState("");
  const [time, setTime] = useState("");
  const [medicalRecord, setMedicalRecord] = useState({
    dogId: dogInfo.dogId,
    recordTime: new Date(),
    hospital: "",
    content: "", // content 필드 추가
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
    const updatedMedicalRecord = {
      ...medicalRecord,
      recordTime: combinedDateTime.toISOString(),
      image: selectedFile,
    };
    console.log(updatedMedicalRecord);
    try {
      const response = await registMedicalRecord(updatedMedicalRecord);
      console.log(response)
      
      handleBack();
    } catch (error) {
      console.error("병원 진료 기록 등록 실패:", error);
      
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
          value={medicalRecord.hospital}
          onChange={(e) =>
            setMedicalRecord((prevRecord) => ({
              ...prevRecord,
              hospital: e.target.value,
            }))
          }
        />
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
          value={medicalRecord.cost.toLocaleString("ko-KR") + " 원"}
          onChange={(e) => {
            const onlyNumbers = e.target.value.replace(/[^\d]/g, "");
            setMedicalRecord((prevRecord) => ({
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
          value={medicalRecord.content}
          onChange={(e) =>
            setMedicalRecord((prevRecord) => ({
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

export default MedicalRecordForm;
