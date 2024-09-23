import { useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
// import useSignupStore from "../../store/UseSignupStore";
import { useState } from "react";
import trash from "../../assets/Signup/trash-bg.png";
import picture from "../../assets/Signup/picture.png";

interface inputPetInfo {
  image: File | null;
  name: string;
  gender: string; // 여아 남아
  breed: string;
  birthDate: string;
  weight: number | null;
  isNeutered: boolean;
}

interface SignupState {
  inputPetInfo: inputPetInfo;
  setInputPetInfo: (newInfo: Partial<inputPetInfo>) => void; // 부분적 업데이트를 허용
}

export const DogInfo = () => {
  const navigate = useNavigate();
  const [imageSrc, setImageSrc] = useState<string | null>(null);
  const [inputPetInfo, setInputPetInfo] = useState<inputPetInfo>({
    image: null,
    name: "",
    gender: "",
    breed: "",
    birthDate: "",
    weight: null,
    isNeutered: false,
  });
  // const { inputPetInfo, setInputPetInfo } = useSignupStore(
  //   (state: SignupState) => ({
  //     inputPetInfo: state.inputPetInfo,
  //     setInputPetInfo: state.setInputPetInfo,
  //   })
  // );

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setInputPetInfo((prev) => ({ ...prev, [name]: value }));
    // setPet((prev) => ({ ...prev, [name]: value })); // 상태 업데이트
  };
  const handleImageClick = () => {
    document.getElementById("file-input")?.click();
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files && files.length > 0) {
      const file = files[0];
      // setInputPetInfo({ petImage: file }); // 선택된 파일을 상태에 저장합니다.
      console.log("Selected file:", file);

      // FileReader를 사용하여 이미지 URL 생성
      const reader = new FileReader();
      reader.onloadend = () => {
        setImageSrc(reader.result as string); // 이미지 URL을 상태에 저장
      };
      reader.readAsDataURL(file); // 파일을 Data URL로 읽기
    }
  };

  const [genderError, setGenderError] = useState("");
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault(); // 기본 제출 동작 방지
    if (!inputPetInfo.gender) {
      setGenderError("이 필드를 선택해야 합니다.");
      console.log(genderError);
    } else {
      setGenderError("");
    }

    if (inputPetInfo.gender) {
      navigate("/signup/health");
    }
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col">
      <TopBar pre={"/signup"} title={""} skip={""} />
      <p className="text-xl font-semibold mb-5">
        우리 아이의 <br /> 정보를 알려주세요
      </p>
      <form
        className="flex flex-col justify-between flex-grow"
        onSubmit={handleSubmit}
      >
        <div className="space-y-5">
          {/* 이름 */}
          <div className="flex space-x-5 justify-between">
            <div className="relative">
              <img
                src={imageSrc || picture}
                alt=""
                className={`w-[78px] h-[78px] object-cover border rounded-full ${
                  !imageSrc ? "cursor-pointer" : ""
                }`}
                onClick={!imageSrc ? handleImageClick : undefined}
                style={{ maxWidth: "100%", maxHeight: "100%" }}
              />
              {!imageSrc ? (
                ""
              ) : (
                <img
                  src={trash}
                  alt=""
                  className="absolute bottom-0 right-0 w-7 cursor-pointer"
                  onClick={() => setImageSrc(null)}
                />
              )}
            </div>
            <input
              type="file"
              id="file-input"
              onChange={handleFileChange}
              style={{ display: "none" }} // input을 숨깁니다.
              accept="image/*" // 이미지 파일만 선택 가능하도록 설정
            />
            <div className="space-y-2 flex-1">
              <label htmlFor="" className="block font-medium text-sm">
                이름 <span className="text-main-color">*</span>
              </label>
              <input
                type="text"
                name="name"
                value={inputPetInfo.name}
                onChange={handleChange} // 상태 업데이트
                required
                maxLength={20}
                placeholder="예) 춘삼이"
                className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
              />
            </div>
          </div>
          {/* 성별 */}
          <div className="space-y-2">
            <label htmlFor="" className="block font-medium text-sm">
              성별 <span className="text-main-color">*</span>
            </label>
            <div className="flex justify-between gap-2">
              <div
                className={`px-16 py-3 w-[50%] text-center rounded-lg font-medium text-sm ${
                  inputPetInfo.gender === "여아"
                    ? "bg-[#FFF7E3] border border-main-color text-main-color"
                    : "bg-[#F4F4F4] text-[#73787E]"
                }`}
                onClick={() => {
                  setInputPetInfo((prevState) => ({
                    ...prevState,
                    gender: "여아",
                  }));
                  setGenderError("");
                }}
              >
                <p>여아</p>
              </div>
              <div
                className={`px-16 py-3 w-[50%] text-center rounded-lg font-medium text-sm ${
                  inputPetInfo.gender === "남아"
                    ? "bg-[#FFF7E3] border border-main-color text-main-color"
                    : "bg-[#F4F4F4] text-[#73787E]"
                }`}
                onClick={() => {
                  setInputPetInfo((prevState) => ({
                    ...prevState,
                    gender: "남아",
                  }));
                  setGenderError("");
                }}
              >
                <p>남아</p>
              </div>
            </div>
            {genderError && (
              <p className="text-sm text-red-500">{genderError}</p>
            )}
          </div>
          {/* 반려 동물 타입 */}
          <div className="space-y-2">
            <label htmlFor="" className="block font-medium text-sm">
              견종 <span className="text-main-color">*</span>
            </label>
            <input
              type="text"
              name="breed"
              value={inputPetInfo.breed}
              onChange={handleChange} // 상태 업데이트
              maxLength={20}
              placeholder="예) 말티즈"
              className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
            />
          </div>
          {/* 생년월일 */}
          <div className="space-y-2">
            <label htmlFor="" className="block font-medium text-sm">
              생년월일 <span className="text-[#9FA4A9]">(선택)</span>
            </label>
            <input
              type="text"
              name="birthDate"
              value={inputPetInfo.birthDate}
              onChange={handleChange} // 상태 업데이트
              minLength={6}
              maxLength={6}
              placeholder="예) 240101"
              className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
            />
          </div>
          {/* 몸무게 */}
          <div className="space-y-2">
            <label htmlFor="" className="block font-medium text-sm">
              몸무게 <span className="text-[#9FA4A9]">(선택)</span>
            </label>
            <div className="flex space-x-5 place-items-center">
              <input
                type="number"
                name="weight"
                value={inputPetInfo.weight!}
                onChange={handleChange} // 상태 업데이트
                minLength={6}
                maxLength={6}
                placeholder="예) 2.4"
                className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
              />
              <span>kg</span>
            </div>
          </div>
        </div>
        <Button
          text={"다음"}
          onClick={() => {
            // signup();
          }}
          bgColor={"bg-main-color"}
        ></Button>
      </form>
    </div>
  );
};
