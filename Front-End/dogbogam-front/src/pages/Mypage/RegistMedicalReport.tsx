import { useState , useRef } from "react";
import { useNavigate } from "react-router-dom";
import BackButton from "../../assets/MyPage/BackButton.png";
import addPhotoIcon from "../../assets/MyPage/addPhotoIcon.png";

// 병원 진료 기록 인터페이스
interface MedicalRecord {
  medicalRecordId?: number;
  dogId: number;
  date: string;
  content?: string | null;
  hospital: string;
  imageName?: string | null;
  imageUrl?: string | null;
  createdAt: Date;
  modifiedAt: Date | null;
  medicalTime: string;
}

// 예방 접종 기록 인터페이스
interface VaccinationRecord {
  vaccinationRecordId?: number;
  dogId: number;
  date: string;
  hospital: string;
  vaccinationRound: number;
  vaccinationTime: string;
  content?: string | null;
  imageName?: string | null;
  imageUrl?: string | null;
  createdAt: Date;
  modifiedAt: Date | null;
}

const RegistMedicalReport = () => {
  const navigate = useNavigate();

  // 파일 첨부를 위한 ref
  const fileInputRef = useRef<HTMLInputElement>(null);

  // 사진 미리보기와 삭제 로직을 위한 상태 추가
  const [previewImages, setPreviewImages] = useState<
    { name: string; url: string }[]
  >([]);

  // 파일 첨부 시 처리 함수
  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0]; // 선택한 파일
    if (file) {
      const fileName = file.name;
      const fileUrl = URL.createObjectURL(file); // 임시 URL 생성

      // 미리보기 이미지 상태 업데이트
      setPreviewImages((prevImages) => [
        ...prevImages,
        { name: fileName, url: fileUrl },
      ]);

      if (currentType === "병원 진료") {
        setMedicalRecord((prevRecord) => ({
          ...prevRecord,
          imageName: fileName,
          imageUrl: fileUrl,
        }));
      } else {
        setVaccinationRecord((prevRecord) => ({
          ...prevRecord,
          imageName: fileName,
          imageUrl: fileUrl,
        }));
      }
    }
  };

  // 파일 첨부 버튼을 누르면 input을 클릭하는 함수
  const handleFileButtonClick = () => {
    fileInputRef.current?.click();
  };

  // 사진 삭제 함수
  const handleRemoveImage = (index: number) => {
    setPreviewImages((prevImages) => prevImages.filter((_, i) => i !== index));
  };

  // 병원 진료 기록 상태
  const [medicalRecord, setMedicalRecord] = useState<MedicalRecord>({
    dogId: 1,
    date: new Date().toISOString().slice(0, 10),
    hospital: "",
    medicalTime: "",
    content: null,
    imageName: null,
    imageUrl: null,
    createdAt: new Date(),
    modifiedAt: null,
  });

  // 예방 접종 기록 상태
  const [vaccinationRecord, setVaccinationRecord] = useState<VaccinationRecord>(
    {
      dogId: 1,
      date: new Date().toISOString().slice(0, 10),
      hospital: "",
      vaccinationRound: 1,
      vaccinationTime: "",
      content: null,
      imageName: null,
      imageUrl: null,
      createdAt: new Date(),
      modifiedAt: null,
    }
  );

  const [currentType, setCurrentType] = useState("병원 진료");

  // 뒤로가기 버튼
  const ClickBackButton = () => {
    navigate(-1);
  };

  // 병원 진료 등록 함수
  const ClickSubmitMedical = () => {
    console.log(medicalRecord);
    // 병원 진료 관련 API 호출 로직
  };

  // 예방 접종 등록 함수
  const ClickSubmitVaccination = () => {
    console.log(vaccinationRecord);
    // 예방 접종 관련 API 호출 로직
  };

  // 진료 타입 변경 함수
  const changeType = (type: string) => {
    setCurrentType(type);
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
        진료 기록 추가
      </h1>
      <p className="text-gray-500 text-sm mb-2.5">
        반려견의 진료 기록을 확인하고 등록하세요
      </p>

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
          value={
            currentType === "병원 진료"
              ? medicalRecord.hospital
              : vaccinationRecord.hospital
          }
          onChange={(e) => {
            if (currentType === "병원 진료") {
              setMedicalRecord((prevRecord) => ({
                ...prevRecord,
                hospital: e.target.value,
              }));
            } else {
              setVaccinationRecord((prevRecord) => ({
                ...prevRecord,
                hospital: e.target.value,
              }));
            }
          }}
        />
      </div>

      {/* 진료 구분 */}
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-semibold mb-1">
          진료 구분<span className="text-main-color ml-0.5">*</span>
        </label>
        <div className="flex space-x-4">
          <button
            className={`flex-1 py-2 px-4 rounded-md border text-sm font-medium ${
              currentType === "병원 진료"
                ? "bg-select-category text-main-color border-main-color"
                : "bg-gray-100 text-gray-500 border-gray-100"
            }`}
            onClick={() => changeType("병원 진료")}
          >
            병원 진료
          </button>
          <button
            className={`flex-1 py-2 px-4 rounded-md border text-sm font-medium ${
              currentType === "예방 접종"
                ? "bg-select-category text-main-color border-main-color"
                : "bg-gray-100 text-gray-500 border-gray-100"
            }`}
            onClick={() => changeType("예방 접종")}
          >
            예방 접종
          </button>
        </div>
      </div>

      {/* 예방 접종일 때 렌더링 */}
      {currentType === "예방 접종" && (
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
      )}

      <div className="flex flex-row space-x-4">
        {/* 날짜 선택 */}
        <div className="flex-1 mb-4">
          <label className="block text-gray-700 text-sm font-semibold mb-1">
            날짜<span className="text-main-color ml-0.5">*</span>
          </label>
          <input
            type="date"
            className="w-full py-2 px-3 border rounded-md text-gray-700"
            required
            value={
              currentType === "병원 진료"
                ? medicalRecord.date
                : vaccinationRecord.date
            }
            onChange={(e) => {
              if (currentType === "병원 진료") {
                setMedicalRecord((prevRecord) => ({
                  ...prevRecord,
                  date: e.target.value,
                }));
              } else {
                setVaccinationRecord((prevRecord) => ({
                  ...prevRecord,
                  date: e.target.value,
                }));
              }
            }}
          />
        </div>

        {/* 시간 선택 */}
        <div className="flex-1 mb-4">
          <label className="block text-gray-700 text-sm font-semibold mb-1">
            시간<span className="text-main-color ml-0.5">*</span>
          </label>
          <input
            type="time"
            className="w-full py-2 px-3 border rounded-md text-gray-700"
            required
            onChange={(e) => {
              if (currentType === "병원 진료") {
                setMedicalRecord((prevRecord) => ({
                  ...prevRecord,
                  medicalTime: e.target.value,
                }));
              } else {
                setVaccinationRecord((prevRecord) => ({
                  ...prevRecord,
                  vaccinationTime: e.target.value,
                }));
              }
            }}
          />
        </div>
      </div>

      {/* 검사 결과 및 메모 추가 */}
      <div className="mb-4">
        <label className="block text-gray-500 text-xs mb-1">
          검사 결과지 및 메모 추가(선택)
        </label>
        <div className="flex items-center space-x-2">
          <button
            className="w-7 h-7 flex items-center justify-center my-2"
            onClick={handleFileButtonClick}
          >
            <img src={addPhotoIcon} alt="Add Icon" className="w-7 h-7" />
            <input
              type="file"
              ref={fileInputRef}
              style={{ display: "none" }}
              onChange={handleFileChange}
            />
          </button>
          {/* 첨부된 사진 미리보기 */}
          <div className="flex space-x-2 mt-2">
            {previewImages.map((image, index) => (
              <div key={index} className="relative w-10 h-10">
                <img
                  src={image.url}
                  alt={image.name}
                  className="w-10 h-10 object-cover rounded-md"
                />
                <button
                  className="absolute top-0 right-0 bg-red-500 text-white rounded-full w-4 h-4 flex items-center justify-center"
                  onClick={() => handleRemoveImage(index)}
                >
                  &times;
                </button>
              </div>
            ))}
          </div>
        </div>
        <textarea
          className="flex-1 w-full py-2 px-3 border rounded-md text-gray-700 placeholder:text-sm"
          placeholder="예) 저녁 7시 까지는 금식 입니다"
          value={
            currentType === "병원 진료"
              ? medicalRecord.content || ""
              : vaccinationRecord.content || ""
          }
          onChange={(e) => {
            if (currentType === "병원 진료") {
              setMedicalRecord((prevRecord) => ({
                ...prevRecord,
                content: e.target.value,
              }));
            } else {
              setVaccinationRecord((prevRecord) => ({
                ...prevRecord,
                content: e.target.value,
              }));
            }
          }}
        />
      </div>

      {/* 등록 버튼 */}
      <div className="mt-4">
        {currentType === "병원 진료" ? (
          <button
            className="w-full bg-yellow-400 text-white font-semibold py-3 rounded-lg shadow-md"
            onClick={ClickSubmitMedical}
          >
            등록 하기
          </button>
        ) : (
          <button
            className="w-full bg-yellow-400 text-white font-semibold py-3 rounded-lg shadow-md"
            onClick={ClickSubmitVaccination}
          >
            등록 하기
          </button>
        )}
      </div>
    </div>
  );
};

export default RegistMedicalReport;
