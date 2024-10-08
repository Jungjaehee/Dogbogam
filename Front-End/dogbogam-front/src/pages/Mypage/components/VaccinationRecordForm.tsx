import { useState, useRef, useEffect } from "react";
import addPhotoIcon from "../../../assets/MyPage/addPhotoIcon.png";
import { registVaccination } from "../../../api/vaccinationRecordAPI";
import useUserStore from "../../../store/UseUserStore";
import deleteIcon from "../../../assets/Signup/trash-bg.png"

const VaccinationRecordForm = ({ handleBack }: { handleBack: () => void }) => {
  const { dogInfo } = useUserStore();
  const fileInputRef = useRef<HTMLInputElement>(null);
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [previewUrl, setPreviewUrl] = useState<string | null>(null);

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
      setPreviewUrl(URL.createObjectURL(file)); // 파일 미리보기 URL 생성
    }
  };

  const handleFileButtonClick = () => {
    fileInputRef.current?.click();
  };

  const handleFileRemove = () => {
    setSelectedFile(null); // 선택한 파일 삭제
    setPreviewUrl(null); // 미리보기 URL 제거
    if (fileInputRef.current) {
      fileInputRef.current.value = ""; // 파일 인풋 초기화
    }
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
      console.log(response);
      handleBack();
    } catch (error) {
      console.error("예방 접종 기록 등록 실패:", error);
    }
  };

  useEffect(() => {
    // 컴포넌트 언마운트 시 URL 메모리 해제
    return () => {
      if (previewUrl) {
        URL.revokeObjectURL(previewUrl);
      }
    };
  }, [previewUrl]);

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
          검사 결과지 추가(선택)
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
          {previewUrl && (
            <div className="relative w-12 h-12">
              {/* 원형 이미지 */}
              <img
                src={previewUrl}
                alt="Preview"
                className="rounded-full w-full h-full object-cover"
              />

              {/* 삭제 버튼 */}
              <button
                onClick={handleFileRemove}
                className="absolute bottom-0 right-0 bg-red-500 p-1 rounded-full"
              >
                <img src={deleteIcon} alt="Delete Icon" className="w-3 h-3" />
              </button>
            </div>
          )}
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
