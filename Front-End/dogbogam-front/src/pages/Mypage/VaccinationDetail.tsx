import { useNavigate , useLocation } from "react-router-dom";
import { useEffect , useState } from "react";
import { VaccinationRecord } from "../../models/record.model";
import { checkRound } from "../../utils/calcStatus";
import { formatDate } from "../../utils/calcDate";
import BackButton from "../../assets/MyPage/BackButton.png";
import VaccineIcon from "../../assets/MyPage/vaccineIcon.png";
import NoImageIcon from "../../assets/MyPage/noImageIcon.png";
import { getVaccinationDetail } from "../../api/vaccinationRecordAPI";
import useUserStore from "../../store/UseUserStore";

const VaccinationDetail = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { id } = location.state
  const [vaccinationDetail , setVaccinationDetail] = useState<VaccinationRecord | null>(null)
  const { dogInfo } = useUserStore();

  useEffect(() => {
    const fetchData = async () => {
      const responseData = await getVaccinationDetail(id);
      setVaccinationDetail(responseData);
    };
    fetchData();
  }, [id]);


  const handleBackClick = () => {
    navigate(-1); // 뒤로가기
  };

  const handleDeleteClick = () => {
    console.log("예방 접종 기록 삭제 요청");
  };

  const record = vaccinationDetail; 

  if (!record) {
    return (
      <p>예방 접종 기록을 불러오고 있어요!</p>
    )
  }

  return (
    <div className="h-full flex flex-col pt-6 px-4 bg-gray-0">
      {/* 뒤로가기 버튼 */}
      <button onClick={handleBackClick}>
        <img src={BackButton} alt="Back Button" className="w-7 h-7 mb-2.5" />
      </button>

      {/* 강아지의 예방 접종 기록 제목 */}
      <h1 className="text-xl text-gray-700 font-semibold mb-2">
        {dogInfo.name}의 예방 접종 기록
      </h1>
      <p className="text-gray-500 text-xs mb-2.5">
        성견은 1년 주기로 적정 항목의 예방주사 접종을 권장해요
      </p>

      <div className="mb-4 bg-gray-100 p-4 rounded-lg shadow-md">
        {/* 접종 날짜 - 상단 가운데 정렬 */}
        <div className="text-center mb-2.5">
          <p className="text-gray-500 text-xs">
            {formatDate(record.recordTime)}
          </p>
        </div>

        {/* 아이콘과 텍스트 부분 - 같은 줄에 배치 */}
        <div className="flex items-center">
          {/* 접종 차수 텍스트 */}
          <div className="flex items-center">
            {/* 백신 아이콘 */}
            <img
              src={VaccineIcon}
              alt="Vaccine Icon"
              className="w-8 h-8 mr-4"
            />
            <p className="text-sm text-gray-700 font-semibold">
              {checkRound(record.vaccinationRound)} 완료
            </p>
          </div>
        </div>
        <p className="text-gray-500 text-xs mt-2">
          접종 당일 발열 구토 등의 증상이 관찰되면 즉시 병원 내원하여 진료가
          필요합니다
        </p>
      </div>

      <div className="mb-4 bg-gray-100 p-4 rounded-lg shadow-md">
        <div className="mb-5">
          <label className="block text-gray-500 text-sm font-bold">병원</label>
          <p className="text-gray-700 text-xs font-semibold">
            {record.hospital}
          </p>
        </div>
        {/* 비용으로 바꿀지도*/}
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

export default VaccinationDetail;
