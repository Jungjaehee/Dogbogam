import { useLocation , useNavigate } from "react-router-dom";
import { useEffect , useState } from "react";
import { MedicalRecord } from "../../models/record.model";
import { formatDate } from "../../utils/calcDate";
import BackButton from "../../assets/MyPage/BackButton.png";
import VaccineIcon from "../../assets/MyPage/vaccineIcon.png";
import NoImageIcon from "../../assets/MyPage/noImageIcon.png";
import { getMedicalRecordDetail } from "../../api/medicalRecordAPI";


const MedicalRecordDetail = () => {
  const navigate = useNavigate();

  const location = useLocation();
  const { id } = location.state;

  const [recordDetail, setRecordDetail] = useState<MedicalRecord | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      const responseData = await getMedicalRecordDetail(id);
      setRecordDetail(responseData);
    };
    fetchData();
  }, [id]);

  const handleBackClick = () => {
    navigate(-1); // 뒤로가기
  };

  const handleDeleteClick = () => {
    console.log("예방 접종 기록 삭제 요청");
  };


  if (!recordDetail) {
    return <p>진료 기록을 불러오고 있어요!</p>;
  }

  const record = recordDetail;

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
      {/* 뒤로가기 */}
      <button onClick={handleBackClick}>
        <img src={BackButton} alt="Back Button" className="w-7 h-7 mb-2.5" />
      </button>

      {/* 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">
        {record.dogId}의 진료 기록
      </h1>
      <p className="text-gray-500 text-xs mb-2.5">
        {record.dogId}가 받은 진료내용을 확인하고 추후 진료에 활용할 수 있어요
      </p>

      <div className="mb-4 bg-gray-100 p-4 rounded-lg shadow-md">
        {/* 진료 날짜 */}
        <div className="text-center mb-2.5">
          <p className="text-gray-500 text-xs">
            {formatDate(record.recordTime)}
          </p>
        </div>

        <div className="flex items-center">
          <div className="flex items-center">
            {/* 아이콘 */}
            <img
              src={VaccineIcon}
              alt="Vaccine Icon"
              className="w-8 h-8 mr-4"
            />
            <p className="text-sm text-gray-700 font-semibold">
              {record.content}
            </p>
          </div>
        </div>
        <p className="text-gray-500 text-xs mt-2">
          진료 이후 특이사항이 관찰되면 즉시 병원에 내원하여 상담이 필요합니다.
        </p>
      </div>

      <div className="mb-4 bg-gray-100 p-4 rounded-lg shadow-md">
        <div className="mb-5">
          <label className="block text-gray-500 text-sm font-bold">병원</label>
          <p className="text-gray-700 text-xs font-semibold">
            {record.hospital}
          </p>
        </div>

        {/* 비용 */}
        <div>
          <label className="block text-gray-500 text-sm font-bold">비용</label>
          <p className="text-gray-700 text-xs font-semibold">
            {record.cost.toLocaleString("ko-KR")} 원
          </p>
        </div>
      </div>

      {/* 첨부 이미지 */}
      <label className="block text-gray-700 text-xs font-semibold mb-1">
        진단서 및 영수증
      </label>
      <div className="mb-4 bg-gray-100 p-4 rounded-lg shadow-md">
        {record.imageUrl ? (
          <div className="flex justify-center items-center">
            <img
              src={record.imageUrl}
              alt="진단서 및 영수증 사진"
              className="w-full h-auto object-cover rounded-lg"
            />
          </div>
        ) : (
          <div className="flex justify-center items-center">
            <img
              src={NoImageIcon}
              alt="사진 없음"
              className="w-8 h-8 object-cover rounded-lg"
            />
          </div>
        )}
      </div>

      {/* 삭제 버튼 */}
      <button
        className="w-full bg-yellow-400 text-white font-semibold py-3 rounded-lg shadow-md mt-4"
        onClick={handleDeleteClick}
      >
        내역 삭제
      </button>
    </div>
  );
};

export default MedicalRecordDetail;
