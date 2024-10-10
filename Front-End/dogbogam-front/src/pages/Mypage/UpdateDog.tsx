import { useNavigate } from "react-router-dom";
import { Button } from "../../components/Button";
import { TopBar } from "../../components/Topbar";
import { useState, useEffect } from "react";
import trash from "../../assets/Signup/trash-bg.png";
import picture from "../../assets/Signup/picture.png";
import { updateDogInfo as UpdateDogInfoModel } from "../../models/dog.model";
import useUserStore from "../../store/UseUserStore";
import { deleteDog } from "../../api/dogAPI";

export const UpdateDog = () => {
  const navigate = useNavigate();
  const { dogInfo } = useUserStore();
  const [imageSrc, setImageSrc] = useState<string | null>(null);
  const [updateDogInfo, setUpdateDogInfo] = useState<UpdateDogInfoModel>({
    dogId: dogInfo.dogId,
    image: null,
    name: dogInfo.name,
    breed: dogInfo.breed,
    birth: dogInfo.birth as unknown as string,
    weight: dogInfo.weight as unknown as number,
    isNeutered: dogInfo.isNeutered as unknown as boolean,
  });

  // 이미지 설정
  useEffect(() => {
    setImageSrc(dogInfo.imageUrl || null);
  }, [dogInfo]);

  const clickDeleteButton = () => {
    deleteDog(dogInfo.dogId);
  };

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
      setUpdateDogInfo((prev) => ({ ...prev, image: file }));

      // FileReader를 사용하여 이미지 URL 생성
      const reader = new FileReader();
      reader.onloadend = () => {
        setImageSrc(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
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
      <div className="flex justify-between items-center mb-5">
        <p className="text-xl font-semibold">
          우리 아이의 <br /> 정보 수정하기
        </p>
        <button
          className="text-red-500 font-medium text-sm"
          onClick={() => clickDeleteButton()}
        >
          삭제
        </button>
      </div>
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
              style={{ display: "none" }}
              accept="image/*"
            />
            <div className="space-y-2 flex-1">
              <label htmlFor="" className="block font-medium text-sm">
                이름 <span className="text-main-color">*</span>
              </label>
              <input
                type="text"
                name="name"
                value={updateDogInfo.name || ""} // dogInfo 값이 없을 경우 빈 문자열로 처리
                onChange={handleChange}
                placeholder="이름을 입력하세요" // 값이 없으면 placeholder가 표시됨
                required
                maxLength={20}
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
          {/* 견종 */}
          <div className="space-y-2">
            <label htmlFor="" className="block font-medium text-sm">
              견종 <span className="text-main-color">*</span>
            </label>
            <input
              type="text"
              name="breed"
              value={updateDogInfo.breed || ""} // dogInfo 값이 없을 경우 빈 문자열로 처리
              onChange={handleChange}
              placeholder="견종을 입력하세요" // 값이 없으면 placeholder가 표시됨
              maxLength={20}
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
              name="birth"
              value={updateDogInfo.birth || ""} // dogInfo 값이 없을 경우 빈 문자열로 처리
              onChange={handleChange}
              placeholder="예) 240101" // 값이 없으면 placeholder가 표시됨
              minLength={6}
              maxLength={6}
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
                value={updateDogInfo.weight?.toString() || ""} // dogInfo 값이 없을 경우 빈 문자열로 처리
                onChange={handleChange}
                placeholder="몸무게를 입력하세요" // 값이 없으면 placeholder가 표시됨
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

export default UpdateDog;
