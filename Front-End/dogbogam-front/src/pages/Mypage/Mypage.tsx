import DogInfo from "./components/Doginfo";
import AIDiagnosisButton from "./components/Buttons/AIDiagnosisButton";
import MyInsuranceButton from "./components/Buttons/MyInsuranceButton";
import SignUpDay from "./components/SignUpDay";
import imageUrl from "../../assets/saewooIcon.png";
import MedicalRecordButton from "./components/Buttons/MedicalRecordButton";
import { useNavigate } from "react-router-dom";

const MyPage = () => {
  const navigate = useNavigate();

  const dummyDog = {
    dogId: 1,
    memberId: 1,
    isDeleted: false,
    name: "새우",
    breed: "포메라니안",
    gender: "female",
    birthDate: new Date(2015, 5, 15), // 2015년 6월 15일생
    weight: 4.3,
    isNeutered: true,
    imageName: "saewoo.jpg",
    imageUrl: imageUrl,
    createdTime: new Date(2023, 0, 1), // 2023년 1월 1일 가입
    modifiedTime: null,
  };

  const ClickLogout = () => {
    navigate("/login");
  };

  return (
    <div className="h-full flex flex-col bg-gray-0 justify-between pt-6 px-4">
      <div>
        <DogInfo dog={dummyDog} />
        <SignUpDay createdTime={dummyDog.createdTime} />
        <div className="flex space-x-6">
          <AIDiagnosisButton />
          <MyInsuranceButton />
        </div>
        <MedicalRecordButton />
      </div>

      <div className="flex justify-center pb-4">
        <button
          className="text-gray-500 hover:text-red-500"
          onClick={ClickLogout}
        >
          로그아웃
        </button>
      </div>
    </div>
  );
};

export default MyPage;
