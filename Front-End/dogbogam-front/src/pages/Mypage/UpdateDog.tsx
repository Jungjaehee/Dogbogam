import { useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useState } from "react";
import trash from "../../assets/Signup/trash-bg.png";
import picture from "../../assets/Signup/picture.png";
import { updateDogInfo } from "../../models/dog.model";
import useUserStore from "../../store/UseUserStore";
import { deleteDog } from "../../api/dogAPI";

export const UpdateDog = () => {
  const navigate = useNavigate();
  const {dogInfo} = useUserStore();
  const [imageSrc, setImageSrc] = useState<string | null>(null);
  const [updateDogInfo, setUpdateDogInfo] = useState<updateDogInfo>({
    dogId: dogInfo.dogId,
    image: null,
    name: "",
    breed: "",
    birthDate: "",
    weight: 0,
    isNeutered: false,
  });

  const clickDeleteButton = () => {
    deleteDog(dogInfo.dogId)
  }


  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setUpdateDogInfo((prev) => ({ ...prev, [name]: value }));
  };
  const handleImageClick = () => {
    document.getElementById("file-input")?.click();
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files && files.length > 0) {
      const file = files[0];
      setUpdateDogInfo((prev) => ({ ...prev, image: file })); // 선택된 파일을 상태에 저장합니다.
      console.log("Selected file:", file);

      // FileReader를 사용하여 이미지 URL 생성
      const reader = new FileReader();
      reader.onloadend = () => {
        setImageSrc(reader.result as string); // 이미지 URL을 상태에 저장
      };
      reader.readAsDataURL(file); // 파일을 Data URL로 읽기
    }
  };

  
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault(); // 기본 제출 동작 방지
    if (updateDogInfo.weight) {
      navigate("/mypage/updateHealth", {
        state: {
          updateDogInfo,
        },
      });
    }
  };

  return (
    <div className="h-full pt-6 px-4 bg-white flex flex-col">
      <TopBar pre={"/mypage"} title={""} skip={""} />
      <p className="text-xl font-semibold mb-5">
        우리 아이의 <br /> 정보 수정하기
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
                value={updateDogInfo.name}
                onChange={handleChange} // 상태 업데이트
                required
                maxLength={20}
                placeholder={dogInfo.name}
                className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
              />
            </div>
          </div>
          {/* 중성화 여부 */}
          <div className="space-y-2">
            <label htmlFor="" className="block font-medium text-sm">
              중성화 여부 <span className="text-main-color">*</span>
            </label>
            <div className="flex justify-between gap-2">
              <div
                className={`px-16 py-3 w-[50%] text-center rounded-lg font-medium text-sm ${
                  updateDogInfo.isNeutered === true
                    ? "bg-[#FFF7E3] border border-main-color text-main-color"
                    : "bg-[#F4F4F4] text-[#73787E]"
                }`}
                onClick={() => {
                  setUpdateDogInfo((prevState) => ({
                    ...prevState,
                    isNeutered: true,
                  }));
                }}
              >
                <p>O</p>
              </div>
              <div
                className={`px-16 py-3 w-[50%] text-center rounded-lg font-medium text-sm ${
                  updateDogInfo.isNeutered === false
                    ? "bg-[#FFF7E3] border border-main-color text-main-color"
                    : "bg-[#F4F4F4] text-[#73787E]"
                }`}
                onClick={() => {
                  setUpdateDogInfo((prevState) => ({
                    ...prevState,
                    isNeutered: false,
                  }));
                }}
              >
                <p>X</p>
              </div>
            </div>
          </div>
          {/* 반려 동물 타입 */}
          <div className="space-y-2">
            <label htmlFor="" className="block font-medium text-sm">
              견종 <span className="text-main-color">*</span>
            </label>
            <input
              type="text"
              name="breed"
              value={updateDogInfo.breed}
              onChange={handleChange} // 상태 업데이트
              maxLength={20}
              placeholder={dogInfo.breed}
              className="border rounded-lg px-4 py-3 w-full text-sm focus:border-blue-100 focus:outline-none"
            />
          </div>
          {/* 생년월일 */}
          <div className="space-y-2">
            <label htmlFor="" className="block font-medium text-sm">
              생년월일 <span className="text-main-color">*</span>
            </label>
            <input
              type="text"
              name="birthDate"
              value={updateDogInfo.birthDate}
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
              몸무게 <span className="text-main-color">*</span>
            </label>
            <div className="flex space-x-5 place-items-center">
              <input
                type="number"
                name="weight"
                value={updateDogInfo.weight!}
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

        <Button
          text={"반려견 삭제"}
          onClick={() => clickDeleteButton()}
          bgColor={"bg-bad-text"}
        ></Button>
      </form>
    </div>
  );
};

export default UpdateDog;