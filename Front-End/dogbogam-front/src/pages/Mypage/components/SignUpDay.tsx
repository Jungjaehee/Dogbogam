import { calSignUpDate } from "../../../utils/calcDate"; 
import calendarIcon from "../../../assets/MyPage/CalendarIcon.png"; 

const SignUpDay = ({ createdTime }: { createdTime: Date }) => {
    
    // 가입한지 며칠 됬는지 계산
  const useServiceDay = calSignUpDate(createdTime); 
  return (
    <div className="flex items-center justify-center my-5 p-4 bg-gray-100 rounded-lg shadow-md">
      {/* 달력 아이콘 */}
      <img src={calendarIcon} alt="Calendar Icon" className="w-6 h-6 mr-2" />

      {/* 텍스트 */}
      <p className="text-gray-700 text-sm">
        댕이보감과 함께한 지{" "}
        <span className="text-yellow-500 font-bold">{useServiceDay}일</span>
      </p>
    </div>
  );
};

export default SignUpDay;
